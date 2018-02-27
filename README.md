# Kaos-prosjekt

Denne veka skal vi gjennomføre eit kaos-prosjekt, obligatorisk oppgåve 4. Dette er eit omfattande prosjekt, kom tidleg i gang! Det skal utviklast ein applikasjon med grafisk brukargrensesnitt som kan generere ulike fraktale figurar og kaos-figurar. Dette gjev god programmeringstrening, og innsyn i ulike spennande algoritmer. Lag grensesnittet som antyda i figuren under: Eit "tabba" panel øverst der ein velger kva for figur som skal lagast. (Sjå tips på http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html for Swing, JavaFX har noko som heiter TabPane) Det tilhøyrande panelet kan ha ulike felt der brukar kan styre figuren.

Dersom de gjer prosjektet i gruppe på tre, legg de inn alle fire figurane beskrive under. Gruppe på to: Lag tre av figurane. Mandelbrot og Bifurcation skal kunne zoome inn på ynskja område. Gruppe som er godkjent med ein: Lag to av figurane, ein av dei må vera Mandelbrot eller Bifurcation - ha zoom.

Mandelbrot finn du lett på nettet, også koden som skal til. Eg har laga figuren under i gråtonar, bruk fargar om du vil.

Bifurcation (Sjå til dømes http://en.wikipedia.org/wiki/Bifurcation_diagram ) Du kan nytte formelen 
xn+1 = r xn(1 - xn) 
Du kan også nytte formelen  
xn+1 = (r + 1) xn - r xn2

Kanskje brukar lett kan bytte formel?

Cellulær automat - sjå til dømes http://mathworld.wolfram.com/CellularAutomaton.html
og der omtale av "binary, nearest-neighbor, one-dimensional automaton". Brukar må enkelt kunne velge kva for ein av dei 256 mogelege figurane som skal visast.

Conway's "game of life" Sjå t.d. http://www.bitstorm.org/gameoflife/ Brukar må kunne teikne starttilstand, og køyre eit og eit steg, eller kontinuerleg og styre tempo.

Frivillig: Legg gjerne inn fleire lag (og tilhøyrande figurar) i tabbedPane'n. Kan t.d. legge inn det rekursive treet (oblig 2), Sierpinsky og von Koch som du har laga før. Og kanskje animert Hanois tårn!
