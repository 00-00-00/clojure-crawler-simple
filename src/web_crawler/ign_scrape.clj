(ns web_crawler.ign_scrape
  (:require [net.cgrand.enlive-html :as html]))

(def ^:dynamic *base-url* "https://in.ign.com/")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn fetch-headlines []
  (map html/text (html/select (fetch-url *base-url*)[:article :h3 :a])))

(defn fetch-content []
  (map html/text (html/select (fetch-url *base-url*)[:article :p]) ))

(defn print-both[]
  (doseq [line (map #(str "\n\n"%1"\n"%2) (fetch-headlines) (fetch-content))] (println line)))
