@rem Books

@rem XML representation of a book
@rem curl -X GET -H "Accept: application/xml" http://localhost:8080/cdbookstore/rs/items/book/1

@rem JSon representation of a book
@rem curl -X GET -H "Accept: application/json" http://localhost:8080/cdbookstore/rs/items/book/1

@rem XML representation of all the books
@rem curl -X GET -H "Accept: application/xml" http://localhost:8080/cdbookstore/rs/items/books

@rem JSon representation of all the books
@rem curl -X GET -H "Accept: application/json" http://localhost:8080/cdbookstore/rs/items/books

@rem Create a book
@rem curl -X POST --data-binary "{ \"title\":\"H3G3\", \"price\":\"24.0\", \"description\":\"3rd Scifi IT book\", \"illustrations\":\"false\", \"isbn\":\"134-234\", \"nbOfPage\":\"241\" }" -H "Content-Type: application/json" -H "Accept: application/json" http://localhost:8080/cdbookstore/rs/items/book

@rem Deletes a book
@rem curl -X DELETE http://localhost:8080/cdbookstore/rs/items/book/2902


@rem CDs

@rem XML representation of a CD
@rem curl -X GET -H "Accept: application/xml" http://localhost:8080/cdbookstore/rs/items/cd/2401

@rem JSon representation of a CD
@rem curl -X GET -H "Accept: application/json" http://localhost:8080/cdbookstore/rs/items/cd/2401

@rem XML representation of all the CDs
@rem curl -X GET -H "Accept: application/xml" http://localhost:8080/cdbookstore/rs/items/cds

@rem JSon representation of all the CDs
@rem curl -X GET -H "Accept: application/json" http://localhost:8080/cdbookstore/rs/items/cds
