(ns data-structure (:gen-class))

;;Caso as funções first rest e cons, funcionam em uma estrutura de dados 
;;você pode dizer que a estrutura de dados implementa a abstração de sequência.
;;Lists, vectors, sets, and maps implementam isso.

(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(map titleize ["Hamsters" "Ragnarok"])

(map titleize '("Empathy" "Decorating"))

(map titleize #{"Elbows" "Soap Carving"})

(map #(titleize (second %)) {:uncomfortable-thing "Winking"})

;;As funções first, rest e cons respectivamente:
;;Exibe o primeiro valor
;;Exibe o resto dos valores 
;;E adiciona um novo valor no começo

(cons 4 (seq '(1 2 3)))

(first (seq [1 2 3]))

(rest (seq #{1 2 3}))

(seq {:name "Bill Compton" :occupation "Dead mopey guy"})


;;Você pode converter uma sequência em um mapa, simplesmente usando o "into" e colocando um mapa vazio

(into {} (seq {:a 1 :b 2 :c 3}))
(into [] (seq {:a 1 :b 2 :c 3}))

;; Várias coleções como argumentos e tomando uma coleção de funções como um argumento

(map inc [1 2 3])

;;mapeando várias coleções (não se esqueça de colocar a mesma quantidade de args pra todos)
(map str ["a" "b" "c"] ["A" "B" "C"])
;; É como se estivesse fazendo isso:
(list (str "a" "A") (str "b" "B") (str "c" "C"))

;; O exemplo a seguir mostra como você poderia usar esse recurso se fosse um vampiro tentando conter o consumo humano. Você tem dois vetores, um representando a ingestão humana em litros e outro representando a ingestão de criaturas nos últimos quatro dias. A função unify-diet-data pega os dados de um único dia para alimentação humana e de criaturas e unifica os dois em um único mapa:

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)

;;Outra coisa que se pode fazer é passar no mapa uma coleção de funções
;;Nesse exemplo o "stats" itera sobre o vetor de funções
;;Aplicando cada função sobre o "numbers"
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10])

(stats [80 1 44 13 6])

;;é possível utilizar o mapa para recuperar valores associados a keywords no clojure

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Tooth Fairy" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :alias identities)

;;Reduce
;;Um de seus usos é: transformar os valores de um mapa, produzindo um novo mapa com as mesmas chaves, mas com valores atualizados

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {};;começa com um mapa vazio (o segundo argumento) e o constrói usando o primeiro argumento, uma função anônima
        {:max 30 :min 10});; trata o argumento {: max 30: min 10} como uma sequência de vetores, como ([: max 30] [: min 10])
;;O código acima faz algo parecido com isso:

(assoc (assoc {} :max (inc 30))
       :min (inc 10))

;;Também filtra keys pelo seu valor
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})

;;TAKE e DROP 
;;take retorna os primeiros n elementos da sequência, enquanto drop retorna a sequência com os primeiros n elementos removidos

(take 3 [1 2 3 4 5 6 7 8 9 10])

(drop 3 [1 2 3 4 5 6 7 8 9 10])

;; take-while e drop-while São mais interessantes. 
;; Cada um leva uma função de predicado (uma função cujo valor de retorno 
;; é avaliado quanto à verdade ou falsidade) pra determinar quando parar de 
;; executar o take ou drop.

;; suponha, por exemplo, que você tenha um vetor representando entradas em seu 
;; diário de “comida”. Cada entrada contém o mês e o dia, junto com o que você comeu. 
;; Para preservar o espaço, incluiremos apenas algumas entradas:

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

;;E aqui, utilizando o take-while pegamos os valores de janeiro e fevereiro (meses 1 e 2)+
;; O take-while percorre a sequencia dada e aplica a função que você manda
;; Quando alcança a primeira entrada de Março a função retorna false
;; e o take-while retorna os valores antes de ser falso
(take-while #(< (:month %) 3) food-journal)
(take-while (fn [x] (< (:month x) 3)) food-journal);; Sem ser função com #


;;A mesma ideia existe com o drop-while, porém ele continua exibindo 
;;os valores até um teste retornar true

(drop-while #(< (:month %) 3) food-journal)

;;Pode se utilizar os dois juntos
(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))

;; filter e some
;; Use o filter para retornar todos elementos de uma sequencia
;; que resultou true dado uma função

(filter #(< (:human %) 5) food-journal)
(filter (fn [s] (< (:human s) 5)) food-journal)

;;Você pode querer saber se uma coleção tem valores que testam true 
;;para uma função. some faz isso, retornando o primeiro valor verdadeiro
;;(não falso e nem nil)
;;SOME RETORNA TRUE ou FALSE
(some #(> (:critter %) 5) food-journal)
(some #(> (:critter %) 3) food-journal)

;; nesse caso primeiro é verificada se a condição (> (: critter%) 3) 
;; é verdadeira e, em seguida, retorna a entrada quando a condição 
;;é realmente verdadeira.
(some #(and (> (:critter %) 3) %) food-journal)

;; sort e sort-by
;; sorte ordena em ordem crescente

(sort [3 1 2])

;; Para necessidades de sort mais específicas, pode se utilizar o sort-by
;; Que aceita funções para isso, assim definindo a ordem 
(sort-by count ["aaa" "c" "bb"])

;;concat 
;;concat simplesmente acrescenta um valor ao final 
(concat [1 2] [3 4])

;;No caso do map, sabe se que ele primeiro chama o seq na coleção que você passa
;;Mas muitas funções como map e filter retornam uma lazy sequence
;;lazy sequence é uma sequencia de membros que não são calculados até se tentar acessá-los
;;O cálculo dos membros de um seq é chamado de realizing the seq(perceber o seq)

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

;;Essa função demora 1 segundo para pesquisar uma entrada 
(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

;;Essa retorna um record se passar no teste caso contrário retorna false
(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

;; mapeia os números do Seguro Social para os registros do banco de dados e então retorna o primeiro registro que indica qual é vampiro.
(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

;;para saber o tempo de execução de uma função você pode utilizar o time 

(time (vampire-related-details 0))

;; Uma implementação nonlazy do mapa teria primeiro que aplicar detalhes relacionados 
;; ao vampiro a cada membro do número de seguridade social antes de passar o resultado para o 
;; filtro. Como você tem um milhão de suspeitos, isso levaria um milhão de segundos, ou 12 dias, 
;; e metade da sua cidade estaria morta até então! Claro, se for descoberto que o único vampiro é
;;  o último suspeito no registro, ainda levará muito tempo com a versão preguiçosa, mas pelo menos 
;;  há uma boa chance de que não.
;;  
;;  Como o mapa é lazy, ele não aplica detalhes relacionados a vampiros aos números do Seguro 
;;  Social até que você tente acessar o elemento mapeado. Na verdade, o mapa retorna um valor quase instantaneamente:

(time (def mapped-details (map vampire-related-details (range 0 1000000))))

(time (first mapped-details))
;; Esta operação demorou cerca de 32 segundos. 
;; Afinal, você está apenas tentando acessar o primeiro 
;; elemento, então deve ter levado apenas um segundo. 
;; A razão pela qual levou 32 segundos é que Clojure 
;; fragmenta suas lazy seq, o que significa 
;; apenas que sempre que Clojure precisa realizar um 
;; elemento, ele realiza preventivamente alguns dos próximos 
;; elementos também. Neste exemplo, você queria apenas o 
;; primeiro elemento de detalhes mapeados, mas Clojure foi 
;; em frente e preparou os próximos 31 também. Clojure 
;; faz isso porque quase sempre resulta em melhor desempenho. 
;; Felizmente, os elementos lazy seq precisam ser realizados 
;; apenas uma vez. Acessar o primeiro elemento de detalhes mapeados novamente leva quase nenhum tempo:

(time (identify-vampire (range 0 1000000)))

;;Sequencias infinitas 
;;O repeat é uma função que pode criar uma sequencia infinita
;;cria uma sequência em que cada membro é o argumento que você passa

(concat (take 8 (repeat "na")) ["Batman!"])

;;Você também pode usar repeatedly, o que chamará a função fornecida para gerar cada elemento na sequência
(take 10 (repeatedly (fn [] (rand-int 10))))

 ;;em sequencias infinitas as funções como first e take funcionam
;;mesmo que a sequencia seja sempre diferente, infinitamente
;;Porem outras funções que procuram ultimo como last n funcionam
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(first (even-numbers))
(take 10 (even-numbers))

;; Collection abstraction
;;Assim como a abstração de sequencia, também tem map, set e list, além de vetor
;;Operam os membros individualmente, enquanto a abstração de coleção trata da estrutura de dados como um todo
;;Como as funções count empty? every?

(empty? [])

(empty? ["NO!"])

;;into 
;;muitas funções seq retornam seq e não a estrutura original. 
;;Com o into, ele retorna a estrutura original

(map identity {:sunlight-reaction "Glitter!"})

(into {} (map identity {:sunlight-reaction "Glitter!"}))
;;Com o into ele pega aquilo que virou uma seq e transforma novamente em mapa
(map identity [:garlic :sesame-oil :fried-eggs])

(into [] (map identity [:garlic :sesame-oil :fried-eggs]))

;;No exemplo a seguir, começamos com um vetor com duas entradas idênticas, map 
;;converte em uma lista e, em seguida, usamos em para inserir os valores em um conjunto.

(map identity [:garlic-clove :garlic-clove])

(into #{} (map identity [:garlic-clove :garlic-clove]))

;; o primeiro argumento do into não precisa estar vazio

(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])

(into ["cherry"] '("pine" "spruce"))

(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                  :relationship-with-teenager "creepy"})

;;conj
;;Conj adiciona elementos em uma coleção 

(conj [0] [1])

(into [0] [1])

(conj [0] 1)

;; Você pode fornecer quantos elementos quiser para adicionar com conj e também pode adicionar a outras coleções, como mapas:
(conj [0] 1 2 3 4)

(conj {:time "midnight"} [:place "ye olde cemetarium"])

;; conj e into são tão semelhantes que você pode até definir conj em termos de em:

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [0] 1 2 3)

;;apply e partial retornam funções e aceitam funções
;;apply explode uma estrutura de dados seqable para que possa ser passada para uma 
;;função que espera um parâmetro rest. Por exemplo, max recebe qualquer número de 
;;argumentos e retorna o maior de todos os argumentos. Veja como você encontrará o maior número:

(max 0 1 2)

(max [0 1 2]);; não retorna o maior valor pois não passou os valores como args

(apply max [0 1 2]);;assim funciona

(defn my-into
  [target additions]
  (apply conj target additions))

(my-into [0] [1 2 3])

;;partial
;;partial recebe uma função e qualquer número de argumentos. 
;;Em seguida, retorna uma nova função. 
;;Quando você chama a função retornada, ela chama a 
;;função original com os argumentos originais 
;;fornecidos junto com os novos argumentos.

(def add10 (partial + 10))
(add10 3)

(add10 15)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))
(add-missing-elements "unobtainium" "adamantium")

(fn [& more-args]
  (apply + (into [20] more-args)))

;; Em geral, você deseja usar parciais quando descobrir que 
;; está repetindo a mesma combinação de função e argumentos 
;; em muitos contextos diferentes. Este exemplo de brinquedo 
;; mostra como você pode usar partial para especializar um 
;; logger, criando uma função warn:
(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))

(warn "Red light ahead")

;; complement

(defn identify-humans
  [social-security-numbers]
  (filter #(not (vampire? %))
          (map vampire-related-details social-security-numbers)))

(def not-vampire? (complement vampire?))
(defn identify-humans
  [social-security-numbers]
  (filter not-vampire?
          (map vampire-related-details social-security-numbers)))


(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))

(def my-pos? (complement neg?))

(my-pos? 1)

(my-pos? -1)

