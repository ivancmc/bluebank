node {
    withMaven(maven:'maven') {
        stage('Checkout') {
            git url: 'https://github.com/ivancmc/bluebank.git', branch: 'develop'
        }
        stage('Build Eureka') {
            dir ('Servidor-Eureka') {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Build Cliente-Conta') {
            dir ('BlueBank') {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Build Transações') {
            dir ('Transacoes') {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage ('Run') {
            sh 'sudo chmod +x /var/run/docker.sock'
            sh 'docker-compose up -d --build --force-recreate'
        }
    }
}
