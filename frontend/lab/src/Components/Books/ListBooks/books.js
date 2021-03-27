import React from 'react';
import {Link} from 'react-router-dom';
import BookTerm from '../BookTerm/bookterm';
import ReactPaginate from 'react-paginate';
class Books extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            page:0,
            size:5
        }
    }
    render() {
        const offset=this.state.size*this.state.page;
        const nextPageOffset=offset+this.state.size;
        const books=this.getBooks(offset,nextPageOffset);
        const pageCount=Math.ceil(this.props.books.length/this.state.size);

        return (
            <div className="container">
                <div class="row">
                    <table className="table table-striped">
                        <thead>
                        <tr>
                            <th scope={"col"}>
                                Name
                            </th>
                            <th scope={"col"}>
                                Author Name
                            </th>
                            <th scope ={"col"}>
                                Author Surname
                            </th>
                            <th scope={"col"}>
                                Category
                            </th>
                            <th scope={"col"}>
                                Available Copies
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        {books}
                        </tbody>
                    </table>
                </div>
                <div className="row">
                    <div className="col-sm-12">
                        <Link className="btn btn-dark" to={"/books/add"} >Add new Book</Link>
                    </div>
                </div>
                <ReactPaginate previousLabel={"back"}
                                nextLabel={"next"}
                               breakLabel={<a href="/#">..</a>}
                                breakClassName={"break-me"}
                                pageClassName={"ml-1"}
                                pageCount={pageCount}
                                marginPagesDisplayed={2}
                                pageRangeDisplayed={7}
                                onPageChange={this.handlePageClick}
                                containerClassName={"pagination"}
                                activeClassName={"active"}/>
            </div>
        )
    }
    handlePageClick = (data) => {
        let selected=data.selected;
        this.setState({
            page:selected
        })
    }
    getBooks = (offset,nextPageOffset) => {
      return  this.props.books.map((term)=>{
            return(
                <BookTerm onDelete={this.props.onDelete} onEdit={this.props.onEdit} onMark={this.props.onMark} term={term}/>

            )
        }).filter((book,index) => {
            return index>=offset && index < nextPageOffset;
        })
    }
}


export default Books;