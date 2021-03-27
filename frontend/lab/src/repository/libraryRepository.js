import axios from "../custom-axios/axios";

const LibraryService = {
    fetchBooks : () => {
        return axios.get("/books")
    },
    fetchAuthors : () => {
      return axios.get("/authors")
    },
    fetchCategories: () => {
        return axios.get("/categories")
    },
    addBook:(name,author,category,availableCopies) =>{
        return axios.post("/books/add",{
          "name":name,
          "author":author,
          "category":category,
          "availableCopies":availableCopies
        })
    },
    deleteBook:(id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    editBook: (id,name,author,category,availableCopies) => {
        return axios.put(`/books/edit/${id}`,{
            "name":name,
            "author":author,
            "category":category,
            "availableCopies":availableCopies
        })
},
    markAsTaken: (id) => {
        return axios.post(`/books/taken/${id}`);
    }

}
export default LibraryService;