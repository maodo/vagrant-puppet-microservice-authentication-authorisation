echo '*** copying frontend files to puppet module'
cd frontend
cp config.js app/js/config.js
npm install
cp -r app/* ../puppet/modules/site_content/files/.
cd ..

echo '*** copying microservices files to puppet module'
cd microservices
gradle assemble
echo 'TODO!'
cd ..
