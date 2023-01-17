(ns functional-assignment.core
  (:gen-class) 
  (:require [clojure.java.io :as io]
            [clojure.tools.reader.edn :as edn]
            [clojure.string :as str]))
(require '[clojure.string :as str])
; define a map
; need to get it in one function / map
; currently has to be upper case - fix this (possibly on taking input)
; this is very simple but will do for now

; probably need to split on characters as well as words (fun)
; try and get the spacing right
(defn asciiMorse [s]
  (let [morse-map {"A" ".-", "B" "-...", "C" "-.-.", "D" "-..", "E" ".",
                   "F" "..-.", "G" "--.", "H" "....", "I" "..", "J" ".---",
                   "K" "-.-", "L" ".-..", "M" "--", "N" "-.", "O" "---",
                   "P" ".--.", "Q" "--.-", "R" ".-.", "S" "...", "T" "-",
                   "U" "..-", "V" "...-", "W" ".--", "X" "-..-", "Y" "-.--",
                   "Z" "--..", " " "       "}]
    (clojure.string/join "   " (map morse-map (str/split s #"")))))
(def morse-code-map {".-" "A", "-..." "B", "-.-." "C", "-.." "D", "." "E", "..-." "F", "--." "G", "...." "H", ".." "I", ".---" "J", "-.-" "K", ".-.." "L", "--" "M", "-." "N", "---" "O", ".--." "P", "--.-" "Q", ".-." "R", "..." "S", "-" "T", "..-" "U", "...-" "V", ".--" "W", "-..-" "X", "-.--" "Y", "--.." "Z", ".----" "1", "..---" "2", "...--" "3", "....-" "4", "....." "5", "-...." "6", "--..." "7", "---.." "8", "----." "9", "-----" "0", "       " " "})
 
; check seven spaces 


; what does a morse-ASCII need to do?
; needs to take morse as a whole string
; split into characters (3 spaces) and words (7 spaces)
; one function to convert one character into ascii?

; first parse into words
; then parse into the individual characters?
; sequence?
; split on 7 spaces 

; function to split into words?
(defn morseWordGrabber [m]
  (str/split m #"       "))
; spec for both conversions, and tests
(defn morseAscii [morse]

  ; need the split on seven spaces before splitting on 3
  (clojure.string/join "" (map morse-code-map (str/split morse #"   ")(str/split morse #"   "))))
; just want to access the other part of it surely?
; not accepting the morse as input - interesting
; (defn morseAscii [code]
;   (let [code-chars  (re-seq #"[^\s]+" code)] 
;     (clojure.string/join " "
;      (map #(get morse-code-map %) code-chars)))) 

; difference is this is a string of morse - not a single character (Except in rare situations) and must be a complete match
; (defn morseAscii [morse] 
;   (for [k v] 
;     :when (some #(= morse %) (morse-code-map))]
;     (println (k)))
; )
; throw all input to upper case


; Q2



; fix use of read-string - not safe
; which regex to split?
; (defn dataGrabber
;   [x]
;   (let [temp-data (slurp "dailyMeanLegacy.txt")
;         temps-strings (clojure.string/split temp-data #"\\s+")
;         temps (map read-string temps-strings)
;         ]
;     (println temps)
;     (println x)
;     ))

;( def tempValues)
; create map


; def what to do with each line


; split, average 
; could chop off the first bit
(defn averager [x]
  (let [line (subs x 10 69) 
        trimstrings (clojure.string/trim line)
        numberstrings (clojure.string/split trimstrings #"\s+")
        numbers (mapv edn/read-string numberstrings)
        result (apply + numbers)
        average (/ result 12)
        ]
    (println "numberstrings is: " numberstrings) 
    (println "trimstrings is: " trimstrings)
    (println "numbers is: " numbers)
    (println "result is: " result)
    (println "average is: " average)
    )
)

  
    ;(println(clojure.string/split (subs x 10) #"\s+")))

; add new values on whitespace
; thus calculating an average
;subvec not substring?


; every 70 characters?
; remove nils, -999s
; run this every how many times - use x as iterator value
; always a nil at the end for some reason

; println side effect 

; this just needs to take a line
; (defn dataGrabber [x, y]
;   (let [file "dailyMeanLegacy.txt"
;         values (str/split (slurp file) #"\s+")
;         lineStart x
;         lineEnd y
;         line (subvec values lineStart lineEnd)
;         date (subvec values 0 2)
;         temps (subvec values 2 15) 
;         numbers (mapv edn/read-string temps)
;         result (apply + numbers)
;         average (/ result 12)]
;     (println "This is the line " line)
;     (println "These is the date " date)
;     (println "These are the temperature values " temps)
;     (println "This is the result " result)
;     (println "This is the average for that day " average))
;   )
; removal of the 999s needs to happen afterwards or breaks iteration
; needs to be applied to every value in the thing i guess
(defn bye999 [line] 
  (remove #{-999} line))
; if we can get this function to work, apply it to numbers

; ;https://stackoverflow.com/questions/36359849/how-to-remove-sequential-matches-in-vector-in-clojure
; (defn remove-999 [v]
;   (mapv str (clojure.string/replace (apply str v) #"-999" "")))



(defn dayAverage [line]
  (let [dropLine (drop 1 line)
        date (nth dropLine 0)
        day (nth dropLine 1)
        tempVec (vec dropLine)
        temps (subvec tempVec 2 14) ; should be a way to do this as sequence. adding should be easier too
        numbers (mapv edn/read-string temps)
        no999s (bye999 numbers)
        result (apply + no999s)
        average (/ result 12)]
        ;numbers (mapv edn/read-string temps)
        ;result (apply + numbers)
        ;average (/ result 12)]
    (println "This is the line " line)
    (println "This is the line " dropLine)
    (println "This is the year " date " and the day " day)
    (println "These are the temperature values " temps)
    (println "These are the numbers " numbers)
    (println "These are the values without negative 999 " no999s)
    (println "This is the result " result)
    (println "This is the average for that day " average))
  ; add average, year to map
  ; then the map can be used for the calculations
  ; this is where we'll get spec in 
  )



; EOLs are ending up on the next line

; get the multiples of x (3-15) for each calendar month in the vector, and add them up (to get averages for each calendar month ever - Q2A)

; iterate over the 12, '31?' times to get all values in a year (so do 'dayAverage' 31 times on values), average them up and use for Q2B - warmest/coldest year

; get the averages for all years 
(defn yearAverage [year]
  )
; file "dailyMeanLegacy.txt"
;         values (str/split (slurp file) #"\s+")
;         lineStart x
;         lineEnd y
;         line (subvec values lineStart lineEnd)

;(dorun (map println(subvec values 0 15)
  ; (with-open [r (io/reader "dailyMeanLegacy.txt")]
  ; (doseq [line (line-seq r)]
  ;   (println (subs line 1 5)) ; this is how we get the key value maybe? tells us the year. set to var, add to map. average all values in year, month, etc
  ;   (println (subs line 9 10)) ; this gives the day value
  ;   ; the rest gives the 12 temp values for each month. use the iterator to determine which month it is 
  ;   (println (clojure.string/split line #"\s+" )) 
  ;   (println (averager line))
  ;   ) 
  ;   )

  ; add to map here 

; load everything into one
; then line by line to the map
; 0-15, repeatedly

; we have the pieces - need to figure out how to iterate through them

; basically, iterate through big values data structure
; add to map
; call function for whatever calculation on the line - feed the function the line
; once this is done, do the spec


(defn -main
  [& args]
  ; decision here
  (println "Press 1 for ASCII-Morse or 2 for Morse-ASCII")
  (let [selection (Integer/parseInt (read-line))] 
    (println "Please supply a value.")
    (cond
      (= selection 1) (let [guess (read-line)]
                         (println (asciiMorse (clojure.string/upper-case guess))))
      (= selection 2) (let [guess (read-line)]
                        (println (morseAscii (clojure.string/upper-case guess))))
      (= selection 3) (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+")
                            line (loop [structure (vec values) emptyLine [] lineStart 0 lineEnd 14]
                                   (if (empty? structure) 
                                     emptyLine 
                                     ; fix this to avoid EOF crashing
                                     (recur
                                      (rest structure)
                                        (let [line (subvec values lineStart (+ lineEnd 1))]
                                          (dayAverage line)) 
                                        (+ lineStart 14) (+ lineEnd 14)))) 
]))))
                            ; iterate here
                            ; call function / add to map
                            
; :else (println "Sorry, please select 1 or 2"))

; legacy format - first column year, second day and month, temps in 1/10 degree
; think about data structure
; use legacy NOT easy
; also need the last 4 files as 1772 to date DOES NOT include
;-32768 for missing data
; first 2 rows can be superflous (averages, etc) - look for the first

; (defn roman-digit-translator [digit]
; (first
;    (filter #(>= digit (first %))
;      [[1000 "M" ]
;       [500  "D" ]
;       [400  "CD"]
;       [100  "C" ]
;      [90   "XC"]
;       [50   "L" ]
;       [40   "XL"]
;       [10   "X" ]
;       [9    "IX"]
;       [5    "V" ]
;       [4    "IV"]
;      [1    "I" ]])))

; (defn roman [n]
; (when (> n 0)
; (let [[n-diff romanNumeral] (roman-digit-translator n)]
;    (apply str romanNumeral(roman(- n n-diff))))))

; (println(roman 9))


; (def numerals {\I 1, \V 5, \X 10, \L 50, \C 100, \D 500, \M 1000})

; (defn numeral-grabber [n y]
;  (if (> n (* 4 y)) (- n y) (+ y n)))

; (defn roman [d]
;  (reduce numeral-grabber (map numerals (reverse d))))

; (println(roman "MCL"))

