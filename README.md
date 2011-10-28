Liquibase plugin for sbt 0.10+ and 0.11+
====================================

# Instructions for use:
### Step 1: Include the plugin in your build

Add the following to your `project/plugins/build.sbt`:

## sbt-0.10.1

    resolvers += "bigtoast-github" at "http://bigtoast.github.com/repo/"

    libraryDependencies += "atd" %% "sbt-liquibase" % "0.2"

## sbt-0.11.0

    resolvers += "bigtoast-github" at "http://bigtoast.github.com/repo/"

    addSbtPlugin("atd" % "sbt-liquibase" % "0.2")

### Step 2: Add sbt-liquibase settings to your build

Add the following to your 'build.sbt' ( if you are using build.sbt )


    import atd.sbtliquibase.LiquibasePlugin

    seq(LiquibasePlugin.liquibaseSettings: _*)

Or if you are using a build object extending from Build:

    import sbt._
    import Keys._
    import atd.sbtliquibase.LiquibasePlugin._

    class MyBuildThatHasntDrankTheNoSQLKoolAid extends Build {
         lazy val seniorProject = Project("hola", file("."), settings = Defaults.defaultSettings ++ liquibaseSettings ++ Seq (
              liquibaseUsername := "dbusername",
              liquibasePassword := "kittensareevil"
              /* lots more liquibase settings available */
         )
    }


## Settings

<table>
        <tr>
                <td> <b>liquibaseUsername</b> </td>
                <td>Username for the database. This defaults to blank.</td>
        </tr>
        <tr><td></td><td>

            liquibaseUsername := "asdf"

        </td></tr>
        <tr>
                <td> <b>liquibasePassword</b> </td>
                <td>Password for the database. This defaults to blank.</td>
        </tr>
        <tr><td></td><td>

            liquibasePassword := "secretstuff"

        </td></tr>
        <tr>
                <td> <b>liqubaseDriver</b> </td>
                <td>Database driver classname. There is no default.</td>
        </tr>
        <tr><td></td><td>

            liquibaseDriver := "com.mysql.jdbc.Driver"

        </td></tr>
        <tr>
                <td> <b>liquibaseUrl</b> </td>
                <td>Database connection uri. There is no default.</td>
        </tr>
        <tr><td></td><td>

            liquibaseUrl := "jdbc:mysql://localhost:3306/mydb"

        </td></tr>
        <tr>
                <td> <b>liquibaseDefaultSchemaName</b> </td>
                <td>Default schema name for the db if it isn't defined in the uri. This defaults to null.</td>
        </tr>
        <tr><td></td><td>

            liquibaseDefaultSchemaName := "dbname"

        </td></tr>
        <tr>
                <td> <b>changelog</b> </td>
                <td>Full path to your changelog file. This defaults 'src/main/migrations/changelog.xml'.</td>
        </tr>
        <tr><td></td><td>

            changelog <<= baseDirectory( _ / "other" / "path" / "dbchanges.xml" absolutePath )

        </td></tr>
</table>

## Tasks

<table>
        <tr>
                <td> <b>liquibase-update</b> </td>
                <td>Run the liquibase migration</td>
        </tr>
        <tr>
                <td><b>liquibase-status</b></td>
                <td>Print count of yet to be run changesets</td>
        </tr>
        <tr>
                <td><b>liquibase-clear-checksums</b></td>
                <td>Removes all saved checksums from database log. Useful for 'MD5Sum Check Failed' errors</td>
        </tr>
        <tr>
                <td><b>liquibase-list-locks</b></td>
                <td>Lists who currently has locks on the database changelog</td>
        </tr>
        <tr>
                <td><b>liquibase-release-locks</b></td>
                <td>Releases all locks on the database changelog.</td>
        </tr>
        <tr>
                <td><b>liquibase-validate-changelog</b></td>
                <td>Checks changelog for errors.</td>
        </tr>
        <tr>
                <td><b>liquibase-db-diff</b></td>
                <td>( this isn't implemented yet ) Generate changeSet(s) to make Test DB match Development</td>
        </tr>
        <tr>
                <td><b>liquibase-db-doc</b></td>
                <td>Generates Javadoc-like documentation based on current database and change log</td>
        </tr>
        <tr>
                <td><b>liquibase-generate-changelog</b></td>
                <td>Writes Change Log XML to copy the current state of the database to the file defined in the changelog setting</td>
        </tr>
        <tr>
                <td><b>liquibase-changelog-sync-sql</b></td>
                <td>Writes SQL to mark all changes as executed in the database to STDOUT</td>
        </tr>

        <tr>
                <td><b>liquibase-tag</b> {tag}</td>
                <td>Tags the current database state for future rollback with {tag}</td>
        </tr>
        <tr>
                <td><b>liquibase-rollback</b> {tag}</td>
                <td>Rolls back the database to the the state is was when the {tag} was applied.</td>
        </tr>
        <tr>
                <td><b>liquibase-rollback-sql</b> {tag}</td>
                <td>Writes SQL to roll back the database to that state it was in when the {tag} was applied to STDOUT</td>
        </tr>
        <tr>
                <td><b>liquibase-rollback-count</b> {int}</td>
                <td>Rolls back the last {int i} change sets applied to the database</td>
        </tr>
        <tr>
                <td><b>liquibase-rollback-sql-count</b> {int}</td>
                <td>Writes SQL to roll back the last {int i} change sets to STDOUT applied to the database</td>
        </tr>

        <tr>
                <td><b>liquibase-rollback-to-date</b> { yyyy-MM-dd HH:mm:ss }</td>
                <td>Rolls back the database to the the state it was at the given date/time. Date Format: yyyy-MM-dd HH:mm:ss</td>
        </tr>
        <tr>
                <td><b>liquibase-rollback-to-date-sql { yyyy-MM-dd HH:mm:ss }</b></td>
                <td>Writes SQL to roll back the database to that state it was in at the given date/time version to STDOUT</td>
        </tr>
        <tr>
                <td><b>liquibase-future-rollback-sql</b></td>
                <td>Writes SQL to roll back the database to the current state after the changes in the changelog have been applied.</td>
        </tr>

</table>


Warnings and Notes
------------------
I needed liquibase in an sbt project and after a little encouragement from a recent BASE (Bay Area Scala Enthusiasts) meeting I thought I would hack it out. Hence, this plugin is still at the alpha stage of development. I have used the update and generate changelog tasks in a production environment. The other tasks have only been used in a test environment. There is not much error handling. This is my first sbt plugin and I was focusing on functionality.

If any bugs are found or features wanted please file an issue in the github project. I will do my best to accommodate.


Acknoledgements
---------------
I used the following plugins as reference

 * sbt-liquibase plugin for sbt 0.7
 * grails liquibase plugin
 * sbt-protobuf plugin for sbt 0.10


