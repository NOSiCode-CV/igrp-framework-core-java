# Deploying and Using a IGRP Core with Sonatype Nexus

This guide explains how to deploy an IGRP Core version to a Nexus repository and use it in a destination application.

---

## 1. Configure `pom.xml` for the Project to Deploy

Update the `pom.xml` of the project you want to deploy to Nexus by adding the distribution management section and necessary metadata, including the Maven deploy plugin.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>cv.igrp</groupId>
    <artifactId>core</artifactId>
    <version>{{the version must be here}}</version>
    <packaging>jar</packaging>
    <name>IGRP Core</name>
    <description>IGRP 3.0 Framework Core</description>
    <url>https://igrp.cv</url>

    <scm>
        <url>http://git.nosi.cv/igrp-3_0/igrp-core</url>
        <connection>scm:git:git.nosi.cv:igrp-3_0/igrp-core.git</connection>
    </scm>

    <distributionManagement>
        <snapshotRepository>
            <id>igrp-framework</id>
            <name>IGRP Framework Snapshots</name>
            <url>https://sonatype.nosi.cv/repository/igrp-framework/</url>
        </snapshotRepository>
    </distributionManagement>

    <licenses>
        <license>
            <name>Apache License 2.0</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        </license>
    </licenses>

    <developers>
        <developer>
            <id>nosi</id>
            <name>NOSi</name>
            <email>helpdesk@nosi.cv</email>
            <url>https://nosi.cv</url>
            <organization>NOSi E.P.E - Núcleo Operacional Para a Sociedade de Informação</organization> <!-- Organization the developer belongs to -->
            <organizationUrl>https://nosi.cv</organizationUrl> <!-- Organization website -->
            <roles>
                <role>developer</role>
            </roles>
            <timezone>-1</timezone>
        </developer>
    </developers>
    <properties>
        <java.version>21</java.version>
    </properties>
    <dependencies>
        <!-- all the dependencies defined here -->
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-deploy-plugin</artifactId>
                <version>3.0.0</version>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

---

## 2. Configure `settings.xml` in the `.m2` Directory

Add your Nexus credentials to the `settings.xml` file, located in your `.m2` directory.

### File: `~/.m2/settings.xml`

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>igrp-horizon</id>
            <username>release-user</username>
            <password>release-password</password>
        </server>
        <server>
            <id>igrp-framework</id>
            <username>snapshot-user</username>
            <password>snapshot-password</password>
        </server>
    </servers>
</settings>
```

---

## 3. Deploy the Artifact

Use the following Maven command to deploy your artifact to Nexus for snapshots:

```bash
mvn clean deploy
```
Use the following Maven command to deploy your artifact to Nexus for releases:
```bash
mvn clean deploy -P release
```

Ensure that the credentials in `settings.xml` and the `distributionManagement` block in the `pom.xml` are correctly configured.

---

## 4. Add the Deployed IGRP Core to a Destination Application

To use the deployed artifact in another application, update the application's `pom.xml` file to include the repository and dependency.

### Add the Repository:

For snapshots

```xml
<repositories>
    <repository>
        <id>igrp-framework</id>
        <name>IGRP Core Framework Repository</name>
        <url>https://sonatype.nosi.cv/repository/igrp-framework/</url>
    </repository>
</repositories>
```

For releases (to be implemented):

```xml
<repositories>
    <repository>
        <id>igrp-horizon</id>
        <name>IGRP Horizon Core Framework Repository</name>
        <url>https://sonatype.nosi.cv/repository/igrp-horizon/</url>
    </repository>
</repositories>
```

### Add the Dependency:

```xml
<dependencies>
    <dependency>
        <groupId>cv.igrp</groupId>
        <artifactId>core</artifactId>
        <version>{{the version in the registry}}</version>
    </dependency>
</dependencies>
```

---

## 5. Verify the Dependency Resolution

Run the following Maven command in the destination application to ensure the dependency is resolved correctly:

```bash
mvn clean install
```
If everything is configured correctly, Maven will download the artifact from the NOSi Nexus Sonatype repository.

---

## Troubleshooting

- Ensure the Nexus URL is reachable and the credentials are valid.
- Verify that the artifact is correctly deployed to the specified repository by accessing the Nexus UI.
- Check for typos in the `groupId`, `artifactId`, or `version` in the destination application's `pom.xml`.