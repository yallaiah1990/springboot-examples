currentBuild.displayName = "employee-management" + currentBuild.number
pipeline {
  agent any
  parameters {
        string(defaultValue: '1.0', description: 'releaseversion', name: 'ReleaseVersion')
        booleanParam(defaultValue: false, description: 'docker image Creation', name: 'dockerimagecreate')
        booleanParam(defaultValue: false, description: 'docker image push to repo', name: 'dockerimagepush')
        //choice(name: 'dockeroption',choices: ['dockerimagecreate', 'dockerimagepush'],description: 'docker options')
  }
  environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
  }
  stages {
    stage('Application Checkout From Git') {
      steps {
        git(url: 'https://github.com/repo-gsr/employee-management.git')
      }
    }
    stage('Application Build') {
      steps {
        bat "mvn clean install -Ddockerfile.skip=true -Dreversion=${params.ReleaseVersion} -Dverbose=true -Dmaven.test.skip=true"
      }
    }
    stage('Application Junit Test') {
      steps {
        bat "mvn test -Dreversion=${params.ReleaseVersion} -Dverbose=true"
      }
    }
    stage('Application Code Coverage') {
      steps {     
        jacoco buildOverBuild: true, changeBuildStatus: true, execPattern: '**/target/**.exec', inclusionPattern: '**/*.class'
      }
    }
    stage('Sonar Analysis') {
       steps {     
          withSonarQubeEnv('sonarqube') {
                    bat "mvn sonar:sonar -Dreversion=${params.ReleaseVersion}"
             }
         }
     }
      /*stage("SonarQube Quality Gate") { 
        timeout(time: 1, unit: 'HOURS') { 
           def qg = waitForQualityGate() 
           if (qg.status != 'OK') {
             error "Job aborted due to quality gate failure: ${qg.status}"
            }
          }
        }*/
        
       stage('Building Docker Image') {
         when{
           expression {params.dockerimagecreate == true}
         }
         steps {     
              bat "mvn dockerfile:build -Dreversion=${params.ReleaseVersion}"
         }
       }
       stage('Push Docker Image To Docker Repo') {
          when{
           expression {params.dockerimagepush == true}
         }
         steps {  
               bat "mvn dockerfile:push -Dreversion=${params.ReleaseVersion} -s settings.xml -gs settings.xml -Dsettings.security=settings-security.xml"
         }
      }
  }
}
