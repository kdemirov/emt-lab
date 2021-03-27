import logo from '../../logo.svg';
import './App.css';
import React,{Component} from 'react';
import {BrowserRouter as Router,Redirect,Route} from "react-router-dom";
import Categories from "../Categories/categories";
import Books from "../Books/ListBooks/books";
import LibraryService from "../../repository/libraryRepository";
import Header from "../Header/header";
import BookAdd from "../Books/AddBook/addbook";
import BookEdit from "../Books/EditBook/editbook";
class App extends Component{

  constructor(props) {
    super(props);
    this.state={
      books:[],
      authors:[],
      categories:[],
      currentBook: {}
    }
  }
  render() {
    return(
        <Router>
            <Header/>
        <main>
            <Route path={"/books/edit/:id"} exact render={() => <BookEdit book={this.state.currentBook} categories={this.state.categories} authors={this.state.authors} onEditBook={this.onEdit}/>}/>
           <Route path={"/books/add"} exact render={() => <BookAdd categories={this.state.categories} authors={this.state.authors} onAddBook={this.addBook} /> }/>
          <Route path={"/books"} exact render={()=> <Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook} onMark={this.markAsTaken}/>}/>
          <Route path={"/categories"} exact render={()=><Categories categories={this.state.categories}/>}/>
          <Redirect to={"/books"}/>
        </main>

        </Router>
    )
  }

  componentDidMount() {
    this.loadBooks()
    this.loadCategories()
    this.loadAuthors()
  }
  markAsTaken =(id) =>{
      LibraryService.markAsTaken(id)
          .then(() => {
              this.loadBooks();
          })
  }
  onEdit = (id,name,author,category,availableCopies) =>{
      LibraryService.editBook(id,name,author,category,availableCopies)
          .then(()=>{
              this.loadBooks();
          })
  }
  getBook = (id) => {
      LibraryService.getBook(id)
          .then((data)=>{
              this.setState({
                  currentBook:data.data
              })
          })
  }
  deleteBook =(id) =>{
      LibraryService.deleteBook(id)
          .then(() =>{
              this.loadBooks();
          })
  }
  addBook =(name,author,category,availableCopies) =>{
      LibraryService.addBook(name,author,category,availableCopies)
          .then(() =>{
              this.loadBooks();
          })
  }
  loadBooks = () => {
    LibraryService.fetchBooks()
        .then((data)=>{
          this.setState({
            books: data.data
          })
        })
  }
  loadCategories = () => {
    LibraryService.fetchCategories()
        .then((data) => {
          this.setState({
            categories : data.data
          })
        })
  }
  loadAuthors = () => {
    LibraryService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        })
  }

}

export default App;
