import React from 'react'

import Person from './person/Person.jsx';
import './Hello.less'

const AppRoot = React.createClass({
    render: function() {
        return (
            <div className="HelloWorld">
                Hello {this.props.who}
                <Person/>
            </div>


        )
    }
})

export default AppRoot