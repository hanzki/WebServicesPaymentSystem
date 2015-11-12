name := "WebServicesPaymentSystem"

version := "1.0"

lazy val `webservicespaymentsystem` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)

libraryDependencies ++= Seq(
  "org.apache.axis" % "axis" % "1.4",
  "org.apache.axis" % "axis-jaxrpc" % "1.4",
  "commons-discovery" % "commons-discovery" % "0.5",
  "wsdl4j" % "wsdl4j" % "1.6.3",
  "javax.activation" % "activation" % "1.1.1",
  "javax.mail" % "mail" % "1.4.7"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  