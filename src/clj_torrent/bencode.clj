(ns clj_torrent.bencode
  (:require [clojure.string :as string]))

(defn decode-bstring [bstring]
  "Decodes a bencoded string. It ensures the length matches the content.
  ie. 4:spam represents the string 'spam'"
  (let [[length content] (string/split bstring #":" 2)]
    (when (= (Integer/parseInt length) (.length content))
      content)))

(defn decode-binteger [binteger]
  "Decodes a bencoded integer.
  ie. i14e"
  (let [first-char (first binteger)
        last-char (last binteger)
        integer (rest (butlast binteger))]

    (if (and (= \i first-char) (= \e last-char))
      (Integer/parseInt (apply str integer))
      (throw (Exception.  "Danger Will Robinson!")))))

(comment
  At the start the only legal characteres would be a 'd', 'l', 'i', or a number.
  If a 'd' or 'l' is encountered open a {} or a () respective and continue parsing until a stray e is found. If an 'i' or an integer is found replace it with
  the clojure primitive. continue until EOF.
  )

(defn find-bstring-or-binteger [string]
  (loop [partial-string (first string)]
    (if (or (decode-binteger partial-string) (decode-bstring partial-string))
      true
      (recur (rest string)))))

(defn decode-blist [blist]
  "Decodes a list and elements inside."
  "I O U"
  )

(defn decode-bdictionary [bdictionary]
  "Decodes a dictionary and elements inside."
  "I O U"
  )

(defn decode-torrent [file-path]
  "Decodes a torrent file and returns a clj representation of the data."
  "I O U"
  )
