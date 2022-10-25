# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Mohammad Abo Khalifa, s354364, s354364@oslomet.no


# Oppgave 1

I oppgave 1 ble koden kopiert fra kompendiet 5.2.3 a), likevel måtte jeg gjøre noen endringer for å gi forelder riktig peker. jeg har to noder, en p og en q, der p er rot, og q er null på starten, jeg har også en cmp, som bruker for å saammenligne input verdi, med p.verdi. Hvis input verdi, er større enn p.verdi, vil jeg gå til høyre, og hvis mindre så vil jeg gå til venstre. til slutt øker jeg antall med en, fordi jeg har lagt til en verdi, og så returnere jeg.

# Oppgave 2

I oppgave 2 så skulle jeg lage antall, jeg starter med å sjekke om verdi er null, om den er det, så får man feilmelding. Jeg lager også en Node p, som starte på rot, og 2 int variabler, en for å telle antall forekomster og en int cmp. Jeg kjører en while loop så lenge p != null. Inne i while kjører jeg comp.compare, for å sjekke om input verdi er større enn p, her får jeg 0 og -1, derfor kjører jeg en if statement som sjekker om cmp < 0, om den er det går jeg til venstre, ellers kjører jeg en if igjen, som sjekker om den er lik 0, om den er det, øker jeg forekomst og setter peker p til p.høyre. Til slutt returnerer jeg forekomst

# Oppgave 3
* Første del av oppgaven skulle jeg løse førstePostorden. jeg startet med å sjekke om Node p var null. Deretter kjørte jeg en while løkke, som skulle kjøre så lenge den var true, så kjører jeg en if statement som sjekker om p.venstre ikke er lik null, om den ikke er det, så setter jeg p = p.venstre(peker p til venstre i noden). Så kjører jeg en else if p.høyre != null, så gjør jeg som jeg gjøre med den første if, men nå flytter jeg pekeren til høyre, ellers skal oppgaven returnete p.

* <strong>I andre del av oppgaven fulgte jeg følgende tekst beskrivelse av postorden</strong>

    * Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
    * Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
    * Hvis p er venstre barn til sin forelder f, gjelder:
        *  Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
        * Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

* I nestePostorden starter jeg med å lage en Node q, som jeg setter lik p.forelder. Jeg starter med å sjekke om q == null, og om den er det, returnerer jeg null. Så ved å følge tekstbeskrivelsene for hva neste vil være dersom ulike krav er utfylt, lager jeg if statements som passer til de og returnerer verdien.

# Oppgave 4
* I postorden vil jeg bruke førstePostorden og nestePostorden for å løse oppgaven. Jeg lager en Node<T> firstNode som kaller til førstePostorden(rot). Jeg bruker en while loop som kjører så lenge firstNode != null. for hver iterasjon av while løkken, vil jeg kalle på oppgave.utførOppgave(firstNode.verdi), så lagrer jeg nestePostorden(firstNode) i firstNode.

* I postordenRecursive gjør jeg det samme som blir gjort i postorden, jeg vil prøve å gå venstre hele veien så lenge det lar seg, og kalle på postordenRecursive, hvis jeg ikke kan gå venstre, prøver jeg å gå høyre, og kalle på postordenRecursive. Til slutt kaller jeg på oppgave.utførOppgave.

# Oppgave 5

* I serialize, startet jeg med å lage instansiere en arraylist som jeg kalte for returnArray, dette arrayet skal bli returnert på slutten, så lagde jeg også en Deque< Node > queue, og lagde en LinkedList. Først så sjekket jeg om rot != null, hvis den ikke er det, så legger jeg rot i queue, ved bruk av queue.add. Jeg kjører også en for loop som kjører så lenge queue ikke er tomt. Inne i while henter jeg første verdien i queue, og lagrer jeg det i en Node temp, og legger temp.verdi inn i arrayet ved bruk av returnArray.add. Jeg sjekker også om temp.venstre ikke er lik null, om den ikke er det, legger jeg temp.venstre i queue.add, og det samme gjør jeg med temp.høyre. Til slutt returnerer jeg arrayet.

* I deserialize oppretter jeg først et tre, og bruker en foreach loop for å kjøre gjennom og legger verdiene fra serialize i tree, til slutt returnerer jeg tree.

# Oppgave 6

* I fjern() ble koden kopiert fra kompendium, men noen endringer måtte bli gjort for at forelder skulle få riktig referanse, og for at man ikke skulle få nullpointer. Først sjekker jeg om verdi er lik null, og hvios den er det skal det returneres false. Deretter går jeg gjennom treet og sammenligner verdier med input verdien som skal fjernes. Hvis verdien ikke blir funnet, skal det bli returnert false. Når man skal fjerne en node i et tre, kan man få noen tilfeller, den <b>første</b> er at jeg har ingen barn, den <b>andre</b> et at treet kun har ett barn, og den <b>tredje</b> er at treet har to barn, derfor kjører jeg if statements for å se etter disse tilfellene.

* I fjernAlle(), bruker jeg metoden antall, og fjern() for å fjerne alt i tabellen. Jeg har også en int counter, som jeg bruker for å redusere antallet med en for hver gang. Jeg kjører en for loop som kjører så lenge i er mindre enn occurence(antallet i treet), inne i denne kaller jeg på fjern(verdi), for å fjerne den/de verdiene, inne i for loopen øker vi counter også med en for hver gang.

* I nullstill kjører jeg en for each loop, og her bruker jeg serialize og fjernAlle metodene. Jeg bruker serialize for å gå gjennom alle verdiene i serialize, og fjerne de ved bruk av fjernAlle. Til slutt setter jeg antallet til 0.