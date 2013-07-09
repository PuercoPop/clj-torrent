(ns clj_torrent.bencode-test
  (:use clojure.test
        clj_torrent.bencode))

(deftest decode-binteger-test
  (is (= 14 (decode-binteger "i14e")))
  (is (= nil (decode-binteger "i14")))
  (is (= nil (decode-binteger "14e")))
  (is (= nil (decode-binteger "14"))))

(deftest decode-bstring-test
  (is (= "spam" (decode-bstring "4:spam")))
  (is (= nil (decode-bstring "3:spam"))))

(deftest decode-blist-test
  (is (= ["spam", "ham"] (decode-blist "l4:spam:3:hame")))
  (is (= nil (decode-blist "l4:spam:3:hame"))))

(deftest decode-bdictionary-test
  (is (= {"cow" "moo" "spam" "eggs"} (decode-bdictionary "d3:cow3:moo4:spam4:eggse")))
  (is (= {"spam" ["a" "b"]} (decode-bdictionary "d4:spaml1:a1:bee")))
  (is (= {"publisher" "bob" "publisher-webpage" "www.example.com" "publisher.location" "home"} (decode-bdictionary "d9:publisher3:bob17:publisher-webpage15:www.example.com18:publisher.location4:homee"))))

(deftest decode-torrent-test
  (is (= {} (decode-torrent (slurp "test/clj_torrent/test.torrent")))))
