(ns functional-assignment.core
  (:gen-class) 
  (:require [clojure.java.io :as io]
            [clojure.tools.reader.edn :as edn]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]))
; define a map
; need to get it in one function / map
; currently has to be upper case - fix this (possibly on taking input)
; this is very simple but will do for now

; probably need to split on characters as well as words (fun)
; try and get the spacing right

; define the morse code regex and do spec for it

(defn asciiMorse [s]
  (let [morse-map {"A" ".-", "B" "-...", "C" "-.-.", "D" "-..", "E" ".",
                   "F" "..-.", "G" "--.", "H" "....", "I" "..", "J" ".---",
                   "K" "-.-", "L" ".-..", "M" "--", "N" "-.", "O" "---",
                   "P" ".--.", "Q" "--.-", "R" ".-.", "S" "...", "T" "-",
                   "U" "..-", "V" "...-", "W" ".--", "X" "-..-", "Y" "-.--",
                   "Z" "--..", " " "       "}]
    (clojure.string/join "   " (map morse-map (str/split s #"")))))


(def string-morse-map "{ 'A':'.-', 'B':'-...',
                    'C':'-.-.', 'D':'-..', 'E':'.',
                    'F':'..-.', 'G':'--.', 'H':'....',
                    'I':'..', 'J':'.---', 'K':'-.-',
                    'L':'.-..', 'M':'--', 'N':'-.',
                    'O':'---', 'P':'.--.', 'Q':'--.-',
                    'R':'.-.', 'S':'...', 'T':'-',
                    'U':'..-', 'V':'...-', 'W':'.--',
                    'X':'-..-', 'Y':'-.--', 'Z':'--..',
                    '1':'.----', '2':'..---', '3':'...--',
                    '4':'....-', '5':'.....', '6':'-....',
                    '7':'--...', '8':'---..', '9':'----.',
                    '0':'-----', ', ':'--..--', '.':'.-.-.-',
                    '?':'..--..', '/':'-..-.', '-':'-....-',
                    '(':'-.--.', ')':'-.--.-'}")

(def morse-map (read-string (str/replace 
                     (str/replace 
                      (str/replace 
                       (str/replace string-morse-map
                                    "\n" "") 
                       "                    " " ")
                      ":" " ")
                     "'" "\"")))
(defn ascii-to-morse [s]
  (str/join " "
            (map #(get morse-map (str/upper-case %)) (str/split s #""))))


(defn morse-to-ascii [morse-ascii]
  (let [zippy (zipmap (vals morse-map) (keys morse-map))]
    (str/join (map #(get 
                     (zipmap (vals morse-map) (keys morse-map)) % " ") 
                   (str/split morse-ascii #" ")))))
; inspired by https://stackoverflow.com/questions/74956079/convert-input-string-into-morse-code-with-clojure
; come back and change and put 3/7 spaces in for char/word

; unit test both of these conversions
; could we do a spec?
 
; check seven spaces 
;functions need to be snake case

; inspired by https://stackoverflow.com/questions/16381835/given-a-clojure-vector-iteratively-remove-1-element
(defn remove-999 [line] 
  (println line)
  (remove #{-999} line))
; if we can get this function to work, apply it to numbers

(defn remove-value [line value]
  (remove #{value} line))

(defn remove-nil [line]
  (remove #{nil?} line))
; ;https://stackoverflow.com/questions/36359849/how-to-remove-sequential-matches-in-vector-in-clojure
; (defn remove-999 [v]
;   (mapv str (clojure.string/replace (apply str v) #"-999" "")))


; record for the map? deffo a spec

; to get a monthly average, pick each n value and repeat every n values until end of DS
; then remove 999s
; then calculate averages

; this is the function to add it to the wider DS

; yearAverage function

; dateVector (for map keys)
; whatevervaluesVector

; to get a monthly average, pick each n value and repeat every n values until end of DS
; then remove 999s
; then calculate averages
; remember to record days
(defrecord TempData [year month day value])
(s/def ::year int? )
(s/def ::month int? )
(s/def ::day int? )
(s/def ::value double? )



(defn dayAverage [line]
  (let [dropLine (drop 1 line)
        date (nth dropLine 0)
        day (nth dropLine 1)
        tempVec (vec dropLine)
        temps (subvec tempVec 2 14) ; should be a way to do this as sequence. adding should be easier too
        numbers (mapv edn/read-string temps)
        no999s (remove-999 numbers)
        result (apply + no999s)
        average (/ result (count no999s)) yearVec []] ; this will need to be flexible, depending on how many 999s have been removed
    ; append average, date/day to map
        ;numbers (mapv edn/read-string temps)
        ;result (apply + numbers)
        ;average (/ result 12)]
    ; (println "This is the line " line)
    ; (println "This is the line " dropLine)
    (println "This is the result for the year " date " and the day " day)
    (println "These are the temperature values, excluding negative 999 results " no999s)
    ;(println "These are the numbers " numbers)
    ;(println "These are the values without negative 999 " no999s)
    ;(println "This is the result " result)
    (println "This is the average temperature for that day of the month, across the year " average)))
    
(def year-keys
  [1772 1773 1774 1775 1776 1777 1778 1779 1780 1781 1782 1783 1784 1785 1786 1787 1788 1789 1790 1791 1792 1793 1794 1795 1796 1797 1798 1799
   1800 1801 1802 1803 1804 1805 1806 1807 1808 1809 1810 1811 1812 1813 1814 1815 1816 1817 1818 1819 1820 1821 1822 1823 1824 1825 1826 1827 1828 1829 1830 1831 1832 1833 1834 1835 1836 1837 1838 1839 1840 1841 1842 1843 1844 1845 1846 1847 1848 1849 1850 1851 1852 1853 1854 1855 1856 1857 1858 1859 1860 1861 1862 1863 1864 1865 1866 1867 1868 1869 1870 1871 1872 1873 1874 1875 1876 1877 1878 1879 1880 1881 1882 1883 1884 1885 1886 1887 1888 1889 1890 1891 1892 1893 1894 1895 1896 1897 1898 1899 
   1900 1901 1902 1903 1904 1905 1906 1907 1908 1909 1910 1911 1912 1913 1914 1915 1916 1917 1918 1919 1920 1921 1922 1923 1924 1925 1926 1927 1928 1929 1930 1931 1932 1933 1934 1935 1936 1937 1938 1939 1940 1941 1942 1943 1944 1945 1946 1947 1948 1949 1950 1951 1952 1953 1954 1955 1956 1957 1958 1959 1960 1961 1962 1963 1964 1965 1966 1967 1968 1969 1970 1971 1972 1973 1974 1975 1976 1977 1978 1979 1980 1981 1982 1983 1984 1985 1986 1987 1988 1989 1990 1991 1992 1993 1994 1995 1996 1997 1998 1999 
   2000 2001 2002 2003 2004 2005 2006 2007 2008 2009 2010 2011 2012 2013 2014 2015 2016 2017 2018 2019 2020 2021 2022
])


; this isn't needed ATM
(defn get-map [vals]
  (let [mapReturn (zipmap (map keyword year-keys) (vec vals))] mapReturn)
  )
; other way of doing it
; get index and compare against yearKeys, thus returning the year


(defn year-average [year line]
  ; get rid of all the actual year stuff
  (let [dropLine (drop 1 line) dropVec (vec dropLine) yearValue (subvec dropVec 0 1) numbers (mapv edn/read-string dropLine) yearLess (remove-value numbers (Integer/parseInt (get yearValue 0))) nineLess (remove-999 yearLess) result (apply + nineLess) sanitisedResult (- result 496) divider (- (count nineLess) 31) average (/ sanitisedResult divider) degreeAverage (double (/ average 10)) intDegreeAverage (num degreeAverage) yearKey (str (get yearValue 0))]
  ; more here
    ; sanitise the count - need to take 31 from it
    ; change year function to remove all
    (println "Full data for " yearValue " " nineLess)
    ; curtail to 2dp 
    ; create and return map pair here?  
    
    (println "This is the result for the year " yearValue ". The sum is " sanitisedResult "and the average is " average " tenths of a degree, and " (double (/ average 10)) " degrees")
    ; return the thing to be added to the map instead 
    intDegreeAverage)
    )



; warmest 2014 : 10.910.9479452054 whatever degrees
; coldest 2022:  7.056666666666667 degrees (influenced by only winter months being available - otherwise it's 1879)

(defn -main
  [& args]
  ; decision here
  (println "Press 1 for ASCII-Morse or 2 for Morse-ASCII. Press 3 to find the warmest day and mean average temp for the given calendar month n. Press 4 to find the warmest and coldest years.")
  (let [selection (Integer/parseInt (read-line))] 
    (println "Please supply a value.")
    (cond
      (= selection 1) (let [input (read-line)]
                         (println (ascii-to-morse (clojure.string/upper-case input))))
      (= selection 2) (let [input (read-line)]
                        (println (morse-to-ascii (clojure.string/upper-case input))))
      (= selection 3)
      (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+") n (Integer/parseInt(read-line)) structure (vec values) ] 
        ;(println "BIG VEC OF THINGS" structure)
        ; start at a value, take everything from that
        (let [firstValue (get structure (+ 2 n)) sanitised (drop 1 structure) sanVec (vec sanitised)  finalVec (subvec sanVec (+ 1 n) (count sanitised)) monthValues (take-nth 14 finalVec) months (mapv edn/read-string monthValues) no999s (remove-999 months) result (apply + no999s) mean (/ result (count no999s)) maximum (apply max 0 months) maxIndex (+ 1 (.indexOf months maximum)) yearBasis (/ maxIndex 31) yearValue (int(+ 1772 yearBasis)) dayValue (rem maxIndex 31)]
          (println "Month number: " n) ; translate to month text, eventually. we'll use a lookup function
          (println "Warmest temp in month:" maximum)
          (println "Mean average temp for month:" mean)
          ; could do mean of given month here tbf
          ; we'll translate this to degrees as well - both need it (or explicitly say tenths of degrees - check spec)
          ; need to work out day value - value is remainder of index / 31
          (println "Found at index:" maxIndex ". Warnest day in month"n"is" dayValue "in the year" yearValue) ; gives us the 'line'. Division by 31 and addition to 1772 gives the year (int values only) - but how do we get the day?
         ; can change to lookup in yearKeys as well
          (println [*print-length* 10] monthValues) 
          ))
      (= selection 4) (let [file "dailyMeanLegacy.txt" values (str/split (slurp file) #"\s+") n (Integer/parseInt(read-line))
                        line (loop [structure (vec values) emptyVec [] lineStart 0 lineEnd 434] ; for year accumulation will probably need to be done at this level (take them all into vectors of size 31*14?)
                                   (if (empty? structure) 
                                     emptyVec 
                                     ; fix this to avoid EOF crashing - we'll need to change it to return a final value at the end.
                                     (recur
                                      (next structure)
                                      (let [line (subvec values lineStart (+ lineEnd 1))]
                                        (conj emptyVec (year-average n line))
                                        ; bear in mind that the last value is not added - not sure why. could be mitigated with if, possibly (as coldest should be 2022)
                                        ; we can now access the yearAverage value
                                        (println "Return value of yearAverage" (year-average n line))
                                        (let [nilVec (remove nil? emptyVec) maximum (apply max 0 nilVec) minimum (apply min 100 nilVec) maxIndex (.indexOf nilVec maximum) minIndex (.indexOf nilVec minimum)]
                                          ; wrap in an if statement to only print at the end
                                          (println "Vector: " nilVec)
                                          ; supply not-found value to nth to avoid breaking
                                          (println "The warmest year was" (nth year-keys maxIndex 0) "and the temperature was"maximum "degrees")
                                          ; do map stuff here maybe? send the nilVec to a function that matches it to a yearKey
                                          ; default values to avoid breaking when vec has nothing in it
                                          (println "The coldest year we have full data for was" (nth year-keys minIndex 0) "and the temperature was"minimum" degrees")
                                          ; this should be 2022 but we're not picking it up (not added to vector for some reason) 2022 also doesn't have full data
                                          ;(println "Minimum index" minIndex)
                                          (println "Map" (get-map nilVec)))
                                        (let [bigConj (year-average n line)]
                                          (println "This is the big conj " bigConj) (conj emptyVec bigConj (println "THE CONJINATOR" emptyVec))))
                             ; replace with year average - THIS IS WHERE WE ADD (some form of conj working with empty lin) ; throw to map - ideally remove nil first ; find highest/lowest and return with their year compatriot
                                      (+ lineStart 434) (+ lineEnd 434)
                                      )
                                     ))
                            ]) 
      
)))
                  