import React from 'react';
import {Link} from 'react-router-dom';

const Nav = () => {
    return (
        <>
            <Link to="/">Home</Link>
            <Link to="/create-ticket">Create Ticket</Link>
        </>
    );
}

export default Nav;