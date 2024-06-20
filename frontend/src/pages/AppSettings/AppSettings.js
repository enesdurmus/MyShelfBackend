import { useState, useEffect } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import { Box, Link, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import { NumericFormat } from 'react-number-format';
import appSettingsService from 'services/appSettingsService';

const headCells = [
    {
        id: 'id',
        align: 'left',
        disablePadding: false,
        label: 'Id'
    },
    {
        id: 'settingKey',
        align: 'left',
        disablePadding: false,
        label: 'Key'
    },
    {
        id: 'settingValue',
        align: 'left',
        disablePadding: true,
        label: 'Value'
    },
    {
        id: 'createdAt',
        align: 'right',
        disablePadding: false,
        label: 'Created At'
    },
    {
        id: 'updatedAt',
        align: 'right',
        disablePadding: false,
        label: 'Updated At'
    },
    {
        id: 'actions',
        align: 'right',
        disablePadding: false,
        label: 'Actions'
    },
];


function OrderTableHead() {
    return (
        <TableHead>
            <TableRow>
                {headCells.map((headCell) => (
                    <TableCell
                        key={headCell.id}
                        align={headCell.align}
                        padding={headCell.disablePadding ? 'none' : 'normal'}
                    >
                        {headCell.label}
                    </TableCell>
                ))}
            </TableRow>
        </TableHead>
    );
}

const AppSettings = () => {
    const [selected] = useState([]);
    const [appSettings, setAppSettings] = useState([]);

    const isSelected = (trackingNo) => selected.indexOf(trackingNo) !== -1;

    useEffect(() => {
        getAppSettings();
    }, []);

    const getAppSettings = async () => {
        const response = await appSettingsService.getAppSettings();
        setAppSettings(response.data);
    }

    return (
        <Box>
            <TableContainer
                sx={{
                    width: '100%',
                    overflowX: 'auto',
                    position: 'relative',
                    display: 'block',
                    maxWidth: '100%',
                    '& td, & th': { whiteSpace: 'nowrap' }
                }}
            >
                <Table
                    aria-labelledby="tableTitle"
                    sx={{
                        '& .MuiTableCell-root:first-of-type': {
                            pl: 2
                        },
                        '& .MuiTableCell-root:last-of-type': {
                            pr: 3
                        }
                    }}
                >
                    <OrderTableHead />
                    <TableBody>
                        {appSettings.map((row, index) => {
                            const isItemSelected = isSelected(row.id);
                            const labelId = `enhanced-table-checkbox-${index}`;

                            return (
                                <TableRow
                                    hover
                                    role="checkbox"
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                    aria-checked={isItemSelected}
                                    tabIndex={-1}
                                    key={row.id}
                                    selected={isItemSelected}
                                >
                                    <TableCell component="th" id={labelId} scope="row" align="left">
                                        <Link color="secondary" component={RouterLink} to="">
                                            {row.id}
                                        </Link>
                                    </TableCell>
                                    <TableCell align="left">{row.setting_key}</TableCell>
                                    <TableCell align="left">{row.setting_value}</TableCell>
                                    <TableCell align="right">{row.created_at}</TableCell>
                                    <TableCell align="right">{row.updated_at}</TableCell>
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
}


export default AppSettings;