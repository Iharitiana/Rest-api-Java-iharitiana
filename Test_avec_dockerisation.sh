#!/usr/bin/bash
echo "Build de l'image docker:"
read -p "Veuillez saisir le nom de l'image a creer: " nom_image
docker build -t $nom_image .
if [ $? -eq 0 ];then 
    echo "Image creer avec succes"
else
    echo "Creation de l'image echouee"
    exit
fi
echo " "
echo "Appuillez sur entrer pour poursuivre..."
read
echo "Construction de l'image:"
read -p "Veuillez saisir le nom du conteneur a creer: " nom_conteneur
docker run -d -p 80:80 -p 8080:8080 --name $nom_conteneur $nom_image
if [ $? -eq 0 ];then 
    echo "Conteneur creer et lancer avec succes"
else
    echo "Construction deu conteneur echouee"
    exit
fi
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
exit
