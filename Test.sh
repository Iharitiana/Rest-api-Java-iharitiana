#!/usr/bin/bash
echo "Compilation du code:"
javac src/*.java
echo "Compilation effectue!"
sleep 2s
echo "Lancement de l'application"
java -cp src Main &
sleep 2s
echo "Application lancee!"
echo " "
echo "Appuillez sur entrer pour poursuivre..."
read
echo "Teste de l'API avec curl :"
echo " "
echo "Ajouter un ticket :"
curl -X POST http://localhost:8080/tickets -d "ticket=001&numeroGuichet=1" 2> /dev/null
if [ $? -eq 0 ];then
    echo "
    Test effectue avec succes"
else
    echo "
    Test echouee"
    exit
fi
echo " "
echo "Entrer pour continuer"
read
echo "Ajouter un ticket 2:"
curl -X POST http://localhost:8080/tickets -d "ticket=002&numeroGuichet=2" 2> /dev/null
if [ $? -eq 0 ];then
    echo "
    Test effectue avec succes"
else
    echo "
    Test echouee"
    exit
fi
echo " "
echo "Entrer pour continuer"
read
echo "Consulter le prochain ticket :"
curl http://localhost:8080/tickets/peek 2> /dev/null
if [ $? -eq 0 ];then
    echo "
    Test effectue avec succes"
else
    echo "
    Test echouee"
    exit
fi
echo " "
echo "Entrer pour continuer"
read
echo "Traiter le prochain ticket :"
curl http://localhost:8080/tickets/next 2> /dev/null
if [ $? -eq 0 ];then
    echo "
    Test effectue avec succes"
else
    echo "
    Test echouee"
    exit
fi
echo " "
echo "Entrer pour continuer"
read
echo "Nombre de tickets en attente :"
curl http://localhost:8080/tickets/count 2> /dev/null
if [ $? -eq 0 ];then
    echo "
    Test effectue avec succes"
else
    echo "
    Test echouee"
    exit
fi
echo " "
echo "Entrer pour continuer"
read 
echo "Traiter le prochain ticket :"
curl http://localhost:8080/tickets/next 2> /dev/null
if [ $? -eq 0 ];then
    echo "
    Test effectue avec succes"
else
    echo "
    Test echouee"
    exit
fi
echo " "
echo "Entrer pour continuer"
read
echo "Nombre de tickets en attente :"
curl http://localhost:8080/tickets/count 2> /dev/null
if [ $? -eq 0 ];then
    echo "
    Test effectue avec succes"
else
    echo "
    Test echouee"
    exit
fi
echo " "
echo "Entrer pour quitter"
read 
kill -9 $(pidof java)

