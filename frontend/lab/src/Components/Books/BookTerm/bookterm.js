import React from 'react';
import {Link} from 'react-router-dom';

const productTerm = (props) =>{
    return(
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.author.name}</td>
            <td scope={"col"}>{props.term.author.surname}</td>
            <td scope={"col"}>{props.term.category}</td>
            <td scope={"col"}>{props.term.availableCopies}</td>
            <a title={"Delete"} className="btn btn-danger"
            onClick={()=>props.onDelete(props.term.id)}>Delete</a>
            <Link className="btn btn-info" to={`/books/edit/${props.term.id}`}
            onClick={() => props.onEdit(props.term.id)}>Edit</Link>
            <button title={"Mark As Taken"} className="btn btn-dark"
            onClick={() => props.onMark(props.term.id)}
            disabled={props.term.availableCopies===0}>Mark as taken</button>

        </tr>
    )
}
export default productTerm;