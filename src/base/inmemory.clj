(ns base.inmemory)

(def values (ref {}))

(defn set
  "Store the given key/value pair."
  [key value]
  (dosync (alter values assoc key value)))

(defn get 
  "Retrieve value for a given key."
  [key]
  (key (deref values)))

(defn delete
  "Delete a given key."
  [key]
  (dosync (alter values dissoc key)))

(defn clear
  "Clear all values from the storage."
  [] (dosync (ref-set values {})))

(defn create-set
  [] true)