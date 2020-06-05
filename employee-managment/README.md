# Employee-management 

### This is Sample Application.

#### Technology Stack

* Spring boot.
* Mongodb.
* Jdk 1.8
* Maven.
* Code Coverage Using Jacoco Plugin.
* Docker.
* Docker-Compose.
* Junit.
* Dozer Mapping.
* Jenkinsfile for pipeline.
* Jwt Token Based Security.
* swagger

Keep on Adding new tools to it...........


#### Spring boot

Spring boot application. it provides lotoff features like autoConfiguration facilty to all configuratons like jdbc and security.

#### Mongodb

MongoDb as backend Database for persisting the application data.

#### Maven

Application Build tool to build the application.

#### Code Coverage Using Jacoco Plugin.

Jacoco plugin used for generating codecoverge report of application.

#### Docker & Docker-compose 

Docker is used for creating application docker images to easly deploy it environment and maintain the application scalability.

Docker-compose used for to up the all the container at a time.

#### Junit

Individual Testcase to test the functionality.

#### Dozer Mapping

This is used for bean to bean mapping in side th application.

#### Jenkinsfile 

Jenkinsfile have stages, each stage have one task like Checkout Application from git and build that application and Junit Testing and CodeCoverage and also create the docker images and push those to private docker repository

and push hole application to sonarQube to static code analysis.

#### Jwt Token Based Security

Provid the tocken based security to all rest apis.

#### swagger

API documention.

#### Steps to try My sample application

* ```git clone https://github.com/repo-gsr/employee-management.git```

* change mongodb creadentials accounrding to you local mongoserver.

* ```mvn clean install -Ddockerfile.skip=true -Dreversion=${ReleaseVersion} -Dverbose=true```

* After building application start the application.
  
  ```mvn spring-boot:run```
  
* access http://localhost:8081


### Docker commands using maven

* Using maven we can create the docker image and push that to docker private repository.

``` mvn dockerfile:build -Dreversion={reversion}```

* For pushing docker images to you private repository you need to add repository details.

	* docker private repository name

	* credentials.

* The above details you need add in maven docker plugin.

``` 
                        <plugin>
				<groupId>com.spotify</groupId>
				<artifactId>dockerfile-maven-plugin</artifactId>
				<version>${dockerfile-maven-version}</version>
				<executions>
					<execution>
						<id>default</id>
						<goals>
							<goal>build</goal>
							<goal>push</goal>
							<goal>tag</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<useMavenSettingsForAuth>true</useMavenSettingsForAuth>
					<repository>You Repository Name</repository>
					<tag>${project.version}</tag>
					<buildArgs>
						<JAR_FILE>target/${project.artifactId}-${project.version}.jar
						</JAR_FILE>
					</buildArgs>
				</configuration>
			</plugin>
```

* In settings.xml file you need add credentials.** User/.m2/setting.xml **

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      https://maven.apache.org/xsd/settings-1.0.0.xsd">
  
  <servers>
    <server>
      <id>docker.io</id>
		<username>XXXXXXXXXXXXXXX</username>
		<password>XXXXXXXXXXXXXXX</password>
    </server>
  </servers>
</settings>

```

* And also add following tag in pom.xml file dockerfile plugin.

```
<useMavenSettingsForAuth>true</useMavenSettingsForAuth>

```

* If  you want to push multiple tag images to private repository. use plugin in following way.

```
                   <plugin>
				<groupId>com.spotify</groupId>
				<artifactId>dockerfile-maven-plugin</artifactId>
				<version>${dockerfile-maven-version}</version>
				<executions>
					<execution>
						<id>tag-latest</id>
						<phase>deploy</phase>
						<goals>
							<goal>build</goal>
							<goal>tag</goal>
							<goal>push</goal>
						</goals>
						<configuration>
							<tag>latest</tag>
						</configuration>
					</execution>
					<execution>
						<id>tag-version</id>
						<phase>deploy</phase>
						<goals>
							<goal>build</goal>
							<goal>tag</goal>
							<goal>push</goal>
						</goals>
						<configuration>
							<tag>${project.artifactId}-${project.version}</tag>
						</configuration>
					</execution>
				</executions>
				<configuration>
					<useMavenSettingsForAuth>true</useMavenSettingsForAuth>
					<repository>private repository_name</repository>
					<buildArgs>
						<JAR_FILE>target/${project.artifactId}-${project.version}.jar
						</JAR_FILE>
					</buildArgs>
				</configuration>
			</plugin>

```

* Use the following maven docker commands

	* ``` mvn dockerfile:build@tag-latest -Dreversion=6.0 ```
	
	* ``` mvn dockerfile:build@tag-version -Dreversion=6.0 ```
	
	* ``` mvn dockerfile:push@tag-letest -Dreversion=6.0 ```
	
	* ``` mvn dockerfile:push@tag-version -Dreversion=6.0 ```
	

### docker dommands


* To remove all running containers
 
	```docker container rm -fv `docker ps -qa` ```

* To Remove all images

	```docker rmi `docker images -qa` ```

* To See the Images 

	``` docker images ```

* To See running Containers

	``` docker ps -a ```

* To Stop the running Container 

	``` docker stop <conatiner_id> ```

* To Remove Container_Id

	``` docker rm <container_id> ```

* To Create the Docker Images

	``` docker build --tag image_Name:version . ```

	* . refers it look for Dockerfile and build that dockerfile and create the image.

* To run the docker images.

	``` docker run -p 8080:8081 -d --name <nameOfContainer> <repository_name> --restart always -e <environement_variable> -v <volume> -t <docker private repository url> ```
	

	* -p  port number that we are binding.
	

	* -d running in  detached mode 
	

	* --name docker container name
	

	* repository_name is image name which image you need run.
	

	* --restart  see the below options

| Flag	| Description |
|-------|-------------|
| no	| Do not automatically restart the container. (the default) |
| on-failure	| Restart the container if it exits due to an error, which manifests as a non-zero exit code. |
| always	| Always restart the container if it stops. If it is manually stopped, it is restarted only when Docker daemon restarts or the container itself is manually restarted. (See the second bullet listed in restart policy details)|
| unless-stopped	| Similar to always, except that when the container is stopped (manually or otherwise), it is not restarted even after Docker daemon restarts.|

### docker-compose dommands

* docker-compose up
	
	To up the all container which are configure in docker-compose file.
	
* docker-compose down

	To down the all container.

* docker-compose restart
	
	To Restart the all containers.

#### Sonar 

* If you want to show the code coverage in SonarQube then first you need to generate code coverage report.

``` ```

* Use the following sonar Command to push the Application to sonarQube Analysis.

``` mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=Login_token -Dreversion=2.0 ```

#### Some Request Input for Application API's

* Adding Employee Data

```
{
  "requestdata": { 
    "firstName": "abc",
    "lastName": "pqr",
    "gmail": "abc@gmail.com",
    "gender": "Male",
    "dateOfJoin": "2019-05-27",
    "dateOfBirth": "1991-05-20",
    "phoneNumber": "1234567890",
    "address": [
      {
        "street": "main road",
        "zipCode": "5600037",
        "city": "Banlore",
        "country": "india",
        "addType": Present
      },
      {
        "street": "KPHB",
        "zipCode": "5160037",
        "city": "Hyderabad",
        "country": "india",
        "addType": Resident
      }
    ],
    "department": {
      "name": "Accounts",
      "description": "account department"
    }
  }
}
```
* Adding Department 

```
{
  "requestdata": { 
      "name": "Accounts",
      "description": "account department"
    }
}

````

# Usage

## Maven Goals

Goals available for this plugin:

| Goal | Description    | Default Phase |
| ---- | -------------- | ------------- |
| `dockerfile:build` | Builds a Docker image from a Dockerfile. | package |
| `dockerfile:tag` | Tags a Docker image. | package |
| `dockerfile:push` | Pushes a Docker image to a repository. | deploy |

### Skip Docker Goals Bound to Maven Phases

You can pass options to maven to disable the docker goals.

| Maven Option  | What Does it Do?           | Default Value |
| ------------- | -------------------------- | ------------- |
| `dockerfile.skip` | Disables the entire dockerfile plugin; all goals become no-ops. | false |
| `dockerfile.build.skip` | Disables the build goal; it becomes a no-op. | false |
| `dockerfile.tag.skip` | Disables the tag goal; it becomes a no-op. | false |
| `dockerfile.push.skip` | Disables the push goal; it becomes a no-op. | false |

For example, to skip the entire dockerfile plugin:
```
mvn clean package -Ddockerfile.skip
```

## Configuration

### Build Phase

| Maven Option  | What Does it Do?           | Required | Default Value |
| ------------- | -------------------------- | -------- | ------------- |
| `dockerfile.contextDirectory` | Directory containing the Dockerfile to build. | yes | none |
| `dockerfile.repository` | The repository to name the built image | no | none |
| `dockerfile.tag` | The tag to apply when building the Dockerfile, which is appended to the repository. | no | latest |
| `dockerfile.build.pullNewerImage` | Updates base images automatically. | no | true |
| `dockerfile.build.noCache` | Do not use cache when building the image. | no | false |
| `dockerfile.build.cacheFrom` | Docker image used as cache-from. Pulled in advance if not exist locally or `pullNewerImage` is `false` | no | none |
| `dockerfile.buildArgs` | Custom build arguments. | no | none |
| `dockerfile.build.squash` | Squash newly built layers into a single new layer (experimental API 1.25+). | no | false |
