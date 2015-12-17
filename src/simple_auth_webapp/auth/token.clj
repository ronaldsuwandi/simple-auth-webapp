(ns simple-auth-webapp.auth.token
  (:require [buddy.core.nonce :as nonce]
            [buddy.core.codecs :as codecs]))

(defn generate-token
  []
  (time (codecs/bytes->base64 (nonce/random-bytes 128))))
