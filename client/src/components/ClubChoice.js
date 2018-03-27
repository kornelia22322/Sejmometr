import React, {Component} from 'react'

class ClubChoice extends Component {
    constructor(props) {
        super(props);
    }

    iteratethroughClubs = (clubs) => {
        return clubs.map((club, index) => {
            return (
                <button type = "button" className = "club-button" id={index} onClick = {(e) => {this.props.onClick(index, e)}}>
                    {club}
                </button>
            )
        })
    }

    render() {
        return (
            <div className = "club-buttons">
                {this.iteratethroughClubs(this.props.clubs)};
            </div>
        )
    }
}

export default ClubChoice;
