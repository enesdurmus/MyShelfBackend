import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import "./AppSettings.css";
import cookies from 'js-cookie';
import TopBar from '../../components/topBar/TopBar';

class AppSettings extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            selectedItem: null,
            newItem: null,
            isConfirmationOpen: false,
            isDeleting: false,
            isUpdating: false,
            isAddAppSettingsOpen: false
        }
    }

    async componentDidMount() {
        try {
            const response = await fetch(`/api/v1/app_settings/user`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    "Authorization": "Bearer " + cookies.get("access_token")
                }
            });

            const responseData = await response.json();
            this.setState({ data: responseData.data });
        } catch (exception) {
            console.error(exception);
        }
    }

    handleInputChange = async (event, item, key) => {
        const value = event.target.value;
        const updatedItem = { ...item, [key]: value };
        const updatedData = this.state.data.map(dataItem => {
            if (dataItem.settings_id === updatedItem.settings_id) {
                return updatedItem;
            }
            return dataItem;
        });
        this.setState({ selectedItem: updatedItem, data: updatedData });
    };

    handleNewSettingsInputChange = async (event, key) => {
        if (event.target.value === event.target.defaultValue) {
            event.target.value = "";
        }
    };

    handleAddSettingsClick = () => {
        this.setState({ isAddAppSettingsOpen: !this.state.isAddAppSettingsOpen });
    };

    handleSaveSettings = () => {
        this.setState({ isAddAppSettingsOpen: !this.state.isAddAppSettingsOpen });
    };

    handleConfirmClick = (item, isUpdating, isDeleting) => {
        this.setState({ selectedItem: item, isConfirmationOpen: true, isUpdating: isUpdating, isDeleting: isDeleting });
    };

    handleCancelClick = () => {
        this.setState({ isConfirmationOpen: false });
    };

    handleConfirmUpdate = async () => {
        const { selectedItem } = this.state;
        try {
            const response = await fetch(`/api/v1/app_settings/admin`, {
                method: 'PUT',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    "Authorization": "Bearer " + cookies.get("access_token")
                },
                body: JSON.stringify(selectedItem)
            });

            if (response.ok) {
            } else {
                console.error("Güncelleme başarısız");
            }
        } catch (error) {
            console.error("Güncelleme hatası:", error);
        } finally {
            this.setState({ isConfirmationOpen: false, isDeleting: false, isUpdating: false });
        }
    };

    handleConfirmDelete = async (item) => {
        try {
            const response = await fetch(`/api/v1/app_settings/admin/delete/${this.state.selectedItem.settings_id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    "Authorization": "Bearer " + cookies.get("access_token")
                }
            });

            if (response.ok) {
                this.componentDidMount();
            } else {
                console.error("Silme başarısız");
            }
        } catch (error) {
            console.error("Silme hatası:", error);
        } finally {
            this.setState({ isConfirmationOpen: false, isDeleting: false, isUpdating: false });
        }
    };

    render() {
        const { data, selectedItem, isConfirmationOpen, isUpdating, isDeleting, isAddAppSettingsOpen } = this.state;

        return (
            <div className="container">
                <TopBar></TopBar>
                <div className="content">
                    <h1>Application Settings</h1>
                    <button className='addButton' onClick={() => this.handleAddSettingsClick()} style={{ cursor: 'pointer' }}>Add App Settings</button>
                    {isAddAppSettingsOpen && <div style={{ display: 'flex', marginBottom: '15px' }}>
                        <input value={"settings name"} onChange={(event) => this.handleNewSettingsInputChange(event, 'settings_key')}></input>
                        <input value={"settings value"} onChange={(event) => this.handleNewSettingsInputChange(event, 'settings_value')} style={{ display: 'flex', marginLeft: '15px' }}></input>
                        <button onClick={() => this.handleSaveSettings()} style={{ display: 'flex', marginLeft: '10px', cursor: 'pointer' }}>Save</button>
                    </div>}
                    <table className="table-striped">
                        <thead>
                            <tr>
                                <th>Settings Key</th>
                                <th>Settings Value</th>
                                <th>Updated At</th>
                                <th>Operations</th>
                            </tr>
                        </thead>
                        <tbody>
                            {data.map((item, index) => (
                                <tr key={item.settings_id}>
                                    <td>
                                        <input
                                            type="text"
                                            value={item.settings_name}
                                            onChange={(event) => this.handleInputChange(event, item, 'settings_name')}
                                        />
                                    </td>
                                    <td>
                                        <input
                                            type="text"
                                            value={item.settings_value}
                                            onChange={(event) => this.handleInputChange(event, item, 'settings_value')}
                                        />
                                    </td>
                                    <td>{item.updated_at}</td>
                                    <td>
                                        <button onClick={() => this.handleConfirmClick(item, true, false)} style={{ cursor: 'pointer' }}>Update</button>
                                        <button onClick={() => this.handleConfirmClick(item, false, true)} style={{ cursor: 'pointer', marginLeft: '7px' }}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                    {isConfirmationOpen && (
                        <div className="confirmation-dialog">
                            <p>İşlemi tamamlamak istediğinizden emin misiniz?</p>
                            {isDeleting && (<button onClick={this.handleConfirmDelete}>Sil</button>)}
                            {isUpdating && (<button onClick={this.handleConfirmUpdate}>Güncelle</button>)}
                            <button onClick={this.handleCancelClick}>Hayır</button>
                        </div>
                    )}
                </div>
            </div >
        );
    }
}

export default AppSettings;