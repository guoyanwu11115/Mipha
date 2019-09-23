#Jenkins构建Alibaba自动化部署脚本
TOMCAT_HOME=/usr/local/tomcat
RESOURCE_DIR=/root/app/temp
APP_NAME=Mipha
echo 'war has been  push to Aliyun'
#备份
copyWar()
{
	time="`date  +%Y%m%d`"
	echo "begin backups...current time:${time}"	
	cd $TOMCAT_HOME/webapps
	tar -cvf ${APP_NAME}.tar.${time} ${APP_NAME}
	echo "begin remove old dir..."
	rm -rf  ${APP_NAME}
	echo "backups has done;"
}
#拷贝war包到webapps目录
getAppWar()
{	
	echo "begin pull new resource war..."
	cd ${RESOURCE_DIR}
	cp ${APP_NAME}.war $TOMCAT_HOME/webapps
	echo "pull new resource war done;"
}
#restart tomcat
restarTomcat()
{
	echo "begin restart tomcat..."
	cd $TOMCAT_HOME/bin 
	sh ./startup.sh  
	pidNew="`ps -ef |grep tomcat |grep -v grep |awk '{print $2}'`"
        if [ "${pidNew}" != "" ] ;then
                echo "restart tomcat success,pid id ${pidNew};"
        else
                echo "restart tomcat failed;"
                
        fi

} 
pid="`ps -ef |grep tomcat |grep -v grep |awk '{print $2}'`"
if [ "${pid}" != "" ] ;then
	echo "tomcat is runiing,pid is ${pid};"
	kill -9 ${pid}
	pidLater="`ps -ef |grep tomcat |grep -v grep |awk '{print $2}'`"
	if [ "${pidLater}" != "" ] ;then
        	echo "tomcat pid kill failed"
	else
        	echo "tomcat pid has been killed"
		#备份，获取新war包
		copyWar
		getAppWar
		restarTomcat
	fi
else
	echo "tomcat pid isn't running"
	#备份，获取新war包
	copyWar
	getAppWar
	restarTomcat
fi

