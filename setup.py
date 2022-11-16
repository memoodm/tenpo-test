import os

#################################
# install architecture packages
#################################

#1. api-gateway
os.chdir("architecture-api-gateway")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir("..")

#2. eureka
os.chdir("architecture-registry-eureka")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir("..")


#################################
# install dto's
#################################

os.chdir(r"service-history\history-service-dto")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir(r"..\..")

os.chdir(r"service-user\user-service-dto")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir(r"..\..")

#################################
# install projects
#################################

os.chdir(r"service-history")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir(r"..")

os.chdir(r"service-mock")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir(r"..")

os.chdir(r"service-user")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir(r"..")

os.chdir(r"service-calculation")
os.system('mvn clean install -Dmaven.test.skip')
os.chdir(r"..")