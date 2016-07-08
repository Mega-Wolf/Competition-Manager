# Competition-Manager
!WICHTIG!
Der Branch, der nach Ablauf der Abgabefrist erstellt wurde, um diese klar voneinader zu trennen. Aus irgendeinem Grund bekamen wir Probleme mit der eigentlichen simpel gedachten Verbindung zwischen Client und Server (anstatt einem Verbindungsobjeckt werden irgendwie viel zu viele erzeugt, aber nciht klar wo überhaupt). Da wir diesem Fehler nachgingen vergaßen wir rechtzeitig Logging und UML einzubauen, welches also erst nach Mitternacht vollends hochgeladen wurde.
Der Fehler bei den Verbindungen wurde leider nicht gefunden, wehalb unser Projekt auch nicht ausführbar ist :(

Update 02:24: Der Fehler wurde gefunden; es wurde zweimal die selbe Liste an Operationen zum Server weitergeleitet...da war wohl jemand müde beim programmieren -.-
Dennoch ist die GUI-Seite nicht vollständig programmiert und all die vielen Teile sind noch nicht so miteinander verbunden, dass sie tatsächlich miteinader funktionieren...Ja das bedeutet, es läuft trotzdem nicht.

In der Dokumentation wurde vergessen die Klasse ServerHelper im Paket network als Factory Klasse hervorzuheben: Mithilfe dieser Klasse (dessen Konstruktor dementsprechend natürlich private ist), lassen sich so z.B.: die Group- oder Round Matches einfach erzeugen, welche dann einfach in er Liste zurückgelifert werden. So muss z.B. der Server nicht selbst wissen, welche Mannschaften nach der Gruppenphase gegeneinander spielen, sondern dies erledigt der ServerHelper. Im ServerHelper sind mehrere solcher Methoden implementiert.
P.S. Im Gegensatz zum Rest, war das tatsächlich schon lange vor Mitternacht da, wurde nur nicht erwähnt. ^^
