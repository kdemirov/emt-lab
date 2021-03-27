import React from 'react';
import {Link} from 'react-router-dom';
const header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand navbar-fixed bg-dark">
                <a className="navbar-brand" href="/books">Library Application</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                   aria-controls="navbarCollapse" aria-expanded="false" >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                    <ul className="navbar-nav">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/books"}>Books</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/categories"}>Categories</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    )
}
export default header;