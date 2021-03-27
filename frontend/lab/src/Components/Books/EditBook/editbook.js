import React from 'react';
import {useHistory} from 'react-router-dom';

const BookEdit = (props) => {
    const history=useHistory();
    const [formData,updateFormDate] = React.useState({
        name:"",
        author:0,
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
        const name=formData.name !== "" ? formData.name :props.book.name;
        const author=formData.author !== 0 ? formData.author: props.book.author.id;
        const category=formData.category !== "" ? formData.category:props.book.category;
        const availableCopies=formData.availableCopies !== 0 ? formData.availableCopies :props.book.availableCopies;
        props.onEditBook(props.book.id,name,author,category,availableCopies);
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
                               name="name"
                               id="name"
                               placeholder={props.book.name}
                               onChange={handleChange}/>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name={"author"} onChange={handleChange} className="form-control">
                            {props.authors.map((term)=>{
                                if(props.book.author !== undefined &&
                                props.book.author.id === term.id)
                                    return <option selected={props.book.author.id} value={term.id}>{term.name} {term.surname}</option>
                                else return <option value={term.id}>{term.name} {term.surname}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name={"category"} onChange={handleChange} className="form-control">
                            {props.categories.map((term)=>{
                                if(props.book.category!== undefined &&
                                props.book.category === term)
                                    return <option selected={props.book.category} value={term}>{term}</option>
                                else return <option value={term}>{term}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label for={"availableCopies"}>Available Copies</label>
                        <input type={"text"} onChange={handleChange} className="form-control" name={"availableCopies"}  id={"availableCopies"}
                                placeholder={props.book.availableCopies}/>
                    </div>
                    <button type={"submit"} className={"btn btn-primary"}>Save</button>
                </form>
            </div>
        </div>
    )
}
export  default BookEdit;