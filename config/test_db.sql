
CREATE TABLE "client" (
                          "id" INT   NOT NULL,
                          "name" VARCHAR   NOT NULL,
                          "site" VARCHAR   NOT NULL,
                          "comment" VARCHAR   NOT NULL,

                          CONSTRAINT "pk_client" PRIMARY KEY ("id")
);

CREATE TABLE "request" (
                           "id" INT   NOT NULL,
                           "date" DATE   NOT NULL,
                           "secret_word" VARCHAR   NOT NULL,
                           "status" VARCHAR   NOT NULL,
                           "client_id" INT   NOT NULL,

                           CONSTRAINT "pk_request" PRIMARY KEY ("id")
);

CREATE TABLE "product" (
                           "id" INT   NOT NULL,
                           "name" VARCHAR   NOT NULL,
                           "weight" INT   NOT NULL,
                           "comment" VARCHAR   NOT NULL,

                           CONSTRAINT "pk_product" PRIMARY KEY ("id")
);

CREATE TABLE "request_product" (
                                   "request_id" INT   NOT NULL,
                                   "product_id" INT   NOT NULL
);

ALTER TABLE "request" ADD CONSTRAINT "fk_request_client_id" FOREIGN KEY("client_id")
    REFERENCES "client" ("id");

ALTER TABLE "request_product" ADD CONSTRAINT "fk_request_product_request_id" FOREIGN KEY("request_id")
    REFERENCES "request" ("id");

ALTER TABLE "request_product" ADD CONSTRAINT "fk_request_product_product_id" FOREIGN KEY("product_id")
    REFERENCES "product" ("id");