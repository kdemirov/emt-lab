import React from 'react';
import {useHistory} from 'react-router-dom';

const BookAdd = (props) => {
    const history=useHistory();
    const [formData,updateFormDate] = React.useState({
        name:"",
        author:1,
        category:"",
        availableCopies:0
    })
    const handleChange = (e) => {
        updateFormDate({
            ...formData,
            [e.target.name]:e.target.value.trim()
        })
    }
    const onFormSubmit = (e) => {
        e.preventDefault();
        const name=formData.name;
        const author=formData.author;
        const category=formData.category;
        const availableCopies=formData.availableCopies;
        props.onAddBook(name,author,category,availableCopies);
        history.push("/books");
    }


    return(
        <div className="row">
            <div className="col-sm-6">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label for={"name"}>Name</label>
                        <input type={"text"}
                               className="form-control"
                               required name="name"
                               id="name"
                               placeholder="Enter Name of Book"
                        onChange={handleChange}/>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name={"author"} onChange={handleChange} className="form-control">
                            {props.authors.map((term)=>
                                <option value={term.id}>{term.name} {term.surname}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name={"category"} onChange={handleChange} className="form-control">
                            {props.categories.map((term)=>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label for={"availableCopies"}>Available Copies</label>
                        <input type={"text"} onChange={handleChange} className="form-control" name={"availableCopies"} required id={"availableCopies"} placeholder={"Enter Available Copies"}/>
                    </div>
                    <button type={"submit"} className={"btn btn-primary"}>Save</button>
                </form>
            </div>
        </div>
    )
}
export  default BookAdd;