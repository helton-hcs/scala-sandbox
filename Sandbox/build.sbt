name := "Sandbox"

version := "0.1"

scalaVersion := "2.11.4"

/** Shell */
shellPrompt := { state => System.getProperty("user.name") + "> " }

/** Dependencies */
resolvers ++= 
    Seq(Resolver.sonatypeRepo("releases"), 
        Resolver.sonatypeRepo("snapshots"),
        Resolver.typesafeRepo("releases"),
        "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases")

libraryDependencies ++= 
	Seq("org.specs2"      %% "specs2-core"           % "2.4.15" % "test",
		"org.specs2"      %% "specs2-matcher-extra"  % "2.4.15" % "test",
		"org.specs2"      %% "specs2-gwt"            % "2.4.15" % "test",
		"org.specs2"      %% "specs2-html"           % "2.4.15" % "test",
		"org.specs2"      %% "specs2-form"           % "2.4.15" % "test",
		"org.specs2"      %% "specs2-scalacheck"     % "2.4.15" % "test",
		"org.specs2"      %% "specs2-mock"           % "2.4.15" % "test",
		"org.specs2"      %% "specs2-junit"          % "2.4.15" % "test")

scalacOptions ++= Seq("-Yrangepos", "-deprecation", "-unchecked", "-feature", "-language:_")

logBuffered := false

/** Console */
initialCommands in console in Test := "import org.specs2._"

