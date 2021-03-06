import sbt._
import Keys._

import com.typesafe.sbteclipse.plugin.EclipsePlugin._

// ----------------------------------------------------------------------------

trait Default {
  val defaultSettings =
    Defaults.coreDefaultSettings ++
    autoImport.eclipseSettings ++ Seq(
      scalaVersion := "2.11.8"
    , crossPaths := false
    , autoScalaLibrary := false
    , unmanagedSourceDirectories in Compile := Seq((javaSource in Compile).value)
    , javacOptions in doc := Seq(
        "-encoding", "UTF-8"
      , "-source", "1.6"
      ) ++ (sys.props("java.specification.version") match {
        case x if x >= "1.8" => Seq("-Xdoclint:none")
        case _ => Nil
      })
    , javacOptions in (Compile, compile) := (javacOptions in doc).value ++ Seq(
        "-target", "1.6"
      , "-deprecation"
      , "-Xlint:all"
      ) ++ (sys.env.get("JDK16_HOME") match {
        case Some(jdk16Home) => Seq(
            "-bootclasspath"
          , Seq("rt", "jsse")
              map(jdk16Home + "/jre/lib/" + _ + ".jar")
              mkString(java.io.File.pathSeparator)
          )
        case _ => Nil
      })
    , EclipseKeys.eclipseOutput := Some(".target")
    , EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE16)
    , EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
    )

  def checkByteCode(jar: File): File = {
    val zipis = new java.util.zip.ZipInputStream(new java.io.FileInputStream(jar))

    Stream.continually(zipis.getNextEntry).takeWhile(null !=).filter(_.getName.endsWith(".class")).foreach{
      entry =>
        J2SEVersionCheck(readIS(zipis), J2SEVersion.`6` == _)
      }
    def readIS(is: java.io.InputStream) = Stream.continually(is.read).takeWhile(-1 !=).map(_.toByte).toArray
    jar
  }
}

// ----------------------------------------------------------------------------

trait Dependencies {
  // JodaTime
  val jodaTime = "joda-time" % "joda-time" % "2.9.4"

  val dslJson = "com.dslplatform" % "dsl-json-joda" % "1.3.1"

  // Json serialization
  val jackson = "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.8"

  // Logging facade
  val slf4j = "org.slf4j" % "slf4j-api" % "1.7.21"

  // Amazon Web Services SDK (S3 type)
  val awsCore = "com.amazonaws" % "aws-java-sdk-core" % "1.10.38"
  val awsS3   = "com.amazonaws" % "aws-java-sdk-s3" % "1.10.38"

  // Android SDK
  val androidSDK = "com.google.android" % "android" % "4.1.1.4"

  // Testing
  val junit = "junit" % "junit" % "4.12"
  val junitInterface = "com.novocode" % "junit-interface" % "0.11"
  val jsonAssert = "org.skyscreamer" % "jsonassert" % "1.2.3"
  val xmlUnit = "xmlunit" % "xmlunit" % "1.6"
  val logback = "ch.qos.logback" % "logback-classic" % "1.1.7"
}

// ----------------------------------------------------------------------------

object Build extends Build with Default with Dependencies {
  lazy val interface = Project(
    "interface"
  , file("interface")
  , settings = defaultSettings ++ Seq(
      name := "dsl-client-interface"
    , libraryDependencies ++= Seq(
        jodaTime
      )
    , unmanagedSourceDirectories in Test := Nil
    )
  )

  lazy val core = Project(
    "core"
  , file("core")
  , settings = defaultSettings ++ Seq(
      name := "dsl-client-core"
    , libraryDependencies ++= Seq(
        slf4j
      , dslJson
      , jackson % "provided"
      , androidSDK % "provided" intransitive()
      , awsCore % "provided"
      , awsS3 % "provided"
      , jsonAssert % "test"
      , junit % "test"
      , junitInterface % "test"
      , logback % "test"
      , xmlUnit % "test"
      )
    , unmanagedSourceDirectories in Test := Seq(
        (javaSource in Test).value
      /* Uncomment next line to enable JSON serialization generated tests */
      , sourceDirectory.value / "test" / "java-generated"
      )
    , testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
    , createVersionProperties
    , unmanagedJars in Test += baseDirectory.value / "test-lib" / "java-client.jar"
    )
  ) dependsOn(interface)

  private val createVersionProperties =
    onLoad := {
      val body = "version=%s\ndate=%s" format (version.value, org.joda.time.LocalDate.now)

      val propertiesPath = (
        baseDirectory.value
        / "src" / "main" / "resources"
        / "com" / "dslplatform" / "client"
        / "dsl-client.properties"
      )

      IO.write(propertiesPath, body)
      onLoad.value
    }

  def aggregatedCompile = ScopeFilter(inProjects(interface, core), inConfigurations(Compile))

  def aggregatedTest = ScopeFilter(inProjects(interface, core), inConfigurations(Test))

  def rootSettings = Seq(
    sources in Compile                        := sources.all(aggregatedCompile).value.flatten,
    unmanagedSources in Compile               := unmanagedSources.all(aggregatedCompile).value.flatten,
    unmanagedSourceDirectories in Compile     := unmanagedSourceDirectories.all(aggregatedCompile).value.flatten,
    unmanagedResourceDirectories in Compile   := unmanagedResourceDirectories.all(aggregatedCompile).value.flatten,
    sources in Test                           := sources.all(aggregatedTest).value.flatten,
    unmanagedSources in Test                  := unmanagedSources.all(aggregatedTest).value.flatten,
    unmanagedSourceDirectories in Test        := unmanagedSourceDirectories.all(aggregatedTest).value.flatten,
    unmanagedResourceDirectories in Test      := unmanagedResourceDirectories.all(aggregatedTest).value.flatten,
    libraryDependencies                       := libraryDependencies.all(aggregatedCompile).value.flatten,
    unmanagedJars in Test                     := unmanagedJars.all(aggregatedTest).value.flatten,
    packageBin in Compile                     := (packageBin in Compile).map{checkByteCode}.value
  )

  lazy val root = project in file(".") settings ((defaultSettings ++ rootSettings): _*)
}
