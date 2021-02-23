pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        slackSend (channel: '#jenkins', color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        sh '''chmod +x ./gradlew
./gradlew clean build --exclude-task test'''
        stash(name: 'build-artifacts', includes: '**/build/libs/*.jar')
      }
    }

    stage('Test') {
      steps {
        sh '''./gradlew test
'''
        stash(name: 'test-artifacts', includes: '**/build/test-results/test/TEST-*.xml')
      }
    }

    stage('Coverage') {
      steps {
        sh './gradlew jacocoTestReport'
        publishCoverage adapters: [jacocoAdapter('build/reports/jacoco/test/jacocoTestReport.xml')]
     }
    }

    stage('Report & Publish') {
      steps {
        unstash 'test-artifacts'
        unstash 'build-artifacts'
        junit '**/build/test-results/test/TEST-*.xml'
        archiveArtifacts 'build/libs/*.jar'
      }
    }
  }
  post {
    success {
      slackSend (channel: '#jenkins', color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
    }

    failure {
      slackSend (channel: '#jenkins', color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
    }
  }
}