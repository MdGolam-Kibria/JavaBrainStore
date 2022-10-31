for i in $(jps -q)
do
    if [[ $(netstat -plnt | grep "$i") == "" ]];then
        echo ""
    else
         echo -------------------------------$i-----------------------------------------------

         echo serviceName = $(ps -ef | grep "${i}" | grep '.jar')
         echo  PID=  $i  
         echo PORT= $(netstat -plnt | grep "${i}")  
        echo LOCATION =  $(pwdx "${i}" | awk '{print $2}') 
    fi
done
