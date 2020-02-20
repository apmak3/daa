# Hajautetut ohjelmistojärjestelmät 2019

# Harjoitustyö 1 tehtävänanto

Ensimmäisessä työssä harjoitellaan säikeiden luontia ja tehtävien jakamista niille. Lisäksi tutkitaan, miten tehtävien suoritusajat vaihtelevat erilaisilla tehtävä tyypeillä. Harjoitus pohjautuu valmiiseen ohjelmarunkoon, johon ryhmän tehtävänä on täydentää puuttuvat osat. ThreadRunner-ohjelma käyttää JavaFx-ympäristöä graafisen käyttöliittymänsä toteutukseen. Harjoitustyön tekemiseen ei kuitenkaan tarvita JavaFx osaamista.

![Semantic description of image](/images/mainwindow.png "Main window")

## Ohjelman käyttöliittymä

- Select task type

   Tällä valinnalla asetetaan tehtävän datajoukon tyyppi. 
- Select worker type

   Asettaa käytettävän tehtävän tyypin.
- Amount of tasks

   Säikeissä ajattavien tehtävien määrä.
- Set load

   Säätää yksittäisen tehtävän suoritusaikaa. 
- Set threadcount

   Asettaa tehtävässä käytettävien säikeiden määrän. Valikon maksimiarvoksi tulee automaattisesti käytössä olevien ytimien 
   määrää vastaava arvo. Oletusarvona on kaksi, joka on virtuaalikoneen oletusprosessorimäärä.
   
- Run static

   Käynnistää tehtävän suorituksen staattisella tehtäväjaolla. Tämä toteutetaan tehtävässä 1.
- Run dynamic

   Käynnistää suorituksen dynaamisella tehtäväjaolla. Tämä toteutetaan tehtävässä 2.

## Ohjelman rakenteen kuvaus

### App.java ja fi.utu.tech.UI pakkaus

![Semantic description of image](/images/AppUIUML.png "App.java ja fi.utu.tech.UI")

App.java on ohjelman pääluokka, joka sisältää main-metodin. Se sisältää myös ajojen käynnistykseen ja suoritusajan mittaamiseen käytetyt metodit. Pakkaus UI sisältää graafiseen käyttöliitymän toimintaan liittyvät luokat.

### fi.utu.tech.tasks

![Semantic description of image](/images/tasksUML.png "fi.utu.tech.tasks")

Tehtävälistojen generointi. Pakkauksesta löytyy erityyppisiä tehtävälistoja generoivia luokkia.

### fi.utu.tech.workers

![Semantic description of image](/images/workersUML.png "fi.utu.tech.workers")

Tästä pakkauksesta löytyvät säikeissä suoritettavat työt. 

### fi.utu.tech.dispatchers

![Semantic description of image](/images/dispatchersUML.png "fi.utu.tech.dispatchers")

Fi.utu.tech.dispatchers pakkaus sisältää töiden säikeille jakamiseen liittyvät toiminteet.

Tämä pakkaus on harjoitustyön suorittamisen kannalta tärkein. Täältä löytyvät ne luokat, StaticDispatcher ja DynamicDispatcher, joissa tehtävissä 1 ja 2 vaaditut töiden jaot säikeille suoritetaan.


## Tehtävät

### Tehtävä 1. Staattinen tehtävänjako

Ensimmäisessä tehtävässä toteutetaan töiden jako säikeille niin, että jokainen säie saa suoritettavakseen kiinteän määrän tehtäviä. Säikeitä luodaan pyydetty määrä ja niistä jokaiselle annetaan yhtä suuri osa generoidusta tehtävälistasta suoritettavaksi. 

Tehtävä ovat kokonaislukumuotoisessa ArrayList-tietorakenteessa, jonka voi jakaa säiemäärää vastaaviin osiin. 

Säikein suoritamien tehtävien suoritusajat vaihtelevat ja säikeet saavat työnsä valmiiksi eri aikoinan. Staattisessa jaossa nopeammin valmistuvat säikeet jäävät odottamaan muiden valmistumista eikä jäljellä olevia tehtäviä jaeta uudelleen jo valmistuneille säikeille.

### Tehtävä 2. Dynaaminen tehtävänjako

Tehtävässä kaksi säikeet suorittavat työtä kuten tehtävässä yksi, mutta nyt työ jaetaan eri tavalla. 

Tarkoituksena on toteuttaa jako niin, että kaikille säikeille riittää suoritettavaa, eivätkä nopeammin valmistuvat säikeet odota hitaampia tekemättä mitään. 

Tämä toteutetaan jakamalla saatu tehtäväjoukko huomattavasti säikeiden määrään suurempaan määrään osajoukkoja, esimerkiksi 10 kertaa säikeiden määrä. Jokaisen ajettava säie poimii tästä osajoukkojen joukosta itselleen tehtäväjoukkoja niin kauan kuin niitä riittää. 
Toisin sanoen aina kun yksi laskenta valmistuu, säie saa úuden tehtävän jonosta.

### Tehtävä 3. Selvitystyö
Vastatkaa seuraaviin kysymyksiin lyhyesti perustellen. Apuna voi käyttää työssä toteutettua ohjelmaa 
ja sillä kokeilla, miten suoritusajat muuttuvat erilaisilla asetuksilla ja tehtävienjakotyypeillä.

1.  Tutustukaa Threadrunner-ohjelman rakenteeseen. Selostakaa lyhyesti, miten ohjelman suoritus kulkee. 

2.	Missä tilanteessa on järkevää käyttää staattista jakoa ja missä dynaamista? Onko tilanteita, jossa on samantekevää, kumpi valintaan? Entä on tilanteita, joissa toinen on selvästi järkevämpi valinta?

3.	Oletetaan, että koneessa voidaan ajaa 8 säiettä aidosti samanaikaisina. Ajatellaan graafisella käyttöliittymällä varustettua ohjelmaa, joka suorittaa raskasta laskentaa säikeissä. Koneessa ajataan samanaikaisesti myös muita ohjelmia. Montako samanaikaista säiettä on järkevää käyttää? Perustele.   

4.	Threadrunner-ohjelman käyttöliittymä odottaa säikeiden valmistumista eikä vastaa sillä aikaa käytäjän pyyntöihin eli on toisin sanoen aivan jumissa. Miten ohjelmaa olisi järkevää muuttaa, jotta käyttöliittymä olisi kokoajan käytössä? Kuvatkaa vastaukseen tarvittavat luokat ja niiden metodit. Metodien toteuksia ei tarvitse tehdä.


