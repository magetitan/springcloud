# !/bash/bin
source /etc/profile
echo '++++++++++++ start deploy.sh date++++++++++++'
sh package.sh
cd ./jars/
sh stop.sh
sh start.sh
echo '++++++++++++ end deploy.sh date++++++++++++'
