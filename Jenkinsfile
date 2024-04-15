// Jenkinsfile
/*
 * Author: Saad Al-Mridha
 * Date: April 15,2024
 * Description: This Jenkinsfile is designed to build, test, and optionally run a Java application.
 *              It is configured to work on a Jenkins server running on Windows, ensuring compatibility
 *              with Windows command syntax while accommodating development on macOS M1.
 */

pipeline {
    agent any  // Specifies that the pipeline can run on any available agent.

    parameters {
        booleanParam(name: 'RUN', defaultValue: false, description: 'Whether to Run the Code')
        string(name: 'BUILD_TYPE', defaultValue: 'Development', description: 'Type of build')
    }

    environment {
        PATH = "${env.PATH}"  
    }

    stages {
        stage('Build') {
            steps {
                echo 'Starting Java Build...'
                bat 'mvn -B -DskipTests clean install'  // Executes Maven with Windows batch command.
                echo 'Java Build Complete.'
            }
        }

        stage('Code Quantity') {
            steps {
                script {
                    // Execute Windows commands to count lines and list files.
                    def lineCount = bat(script: 'find /c /v "" src\\main\\java\\App.java', returnStdout: true).trim()
                    echo "Number of lines in App.java: ${lineCount}"

                    def files = bat(script: "dir /b", returnStdout: true).trim().split("\\r?\\n")
                    echo "Listing files in the repository (top level):"
                    files.each { file ->
                        echo file
                    }
                }
            }
        }

        stage('Test') {
            when {
                expression { params.BUILD_TYPE == 'Development' }
            }
            steps {
                bat 'mvn test'
                junit 'target\\surefire-reports\\*.xml'
            }
        }

        stage('Run') {
            when {
                expression { params.RUN == true }
            }
            steps {
                bat 'call deliver.bat'  // Ensure deliver.bat is prepared for Windows.
            }
        }

        stage('Build Results') {
            steps {
                echo "Build ${params.BUILD_TYPE} completed successfully."
                echo "I have now completed ACIT 4850!"
                echo "Student Number: A01339129, Group Number: 44" 
            }
        }
    }

    post {
        always {
            echo "Pipeline execution complete!"
        }
    }
}
