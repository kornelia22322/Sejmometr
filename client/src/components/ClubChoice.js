import React, {Component} from 'react'

class ClubChoice extends Component {
    constructor(props) {
        super(props);
    }

    iteratethroughClubs = (clubs) => {
        return clubs.map((club, index) => {
            return (
                <button type = "button" className = "club-button" onClick = {() => {this.props.onClick(index)}}>
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
