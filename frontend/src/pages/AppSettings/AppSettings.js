import { useMemo, useState, useEffect } from 'react';
import {
    MRT_EditActionButtons,
    MaterialReactTable,
    useMaterialReactTable,
} from 'material-react-table';
import {
    Box,
    Button,
    DialogActions,
    DialogContent,
    DialogTitle,
    IconButton,
    Tooltip,
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import appSettingsService from 'services/appSettingsService';

export default function AppSettings() {
    const [validationErrors, setValidationErrors] = useState({});
    const [data, setData] = useState([]);
    const [isFetchingData, setIsFetchingData] = useState(false);
    const [isCreatingData, setIsCreatingData] = useState(false);
    const [isUpdatingData, setIsUpdatingData] = useState(false);
    const [isDeletingData, setIsDeletingData] = useState(false);
    const [isFetchError, setIsFetchError] = useState(false);

    useEffect(() => {
        handleFetch();
    }, []);

    const columns = useMemo(
        () => [
            {
                accessorKey: 'id',
                header: 'Id',
                enableEditing: false,
                size: 80,
            },
            {
                accessorKey: 'setting_key',
                header: 'Key',
                muiEditTextFieldProps: {
                    required: true,
                    error: !!validationErrors?.firstName,
                    helperText: validationErrors?.firstName,
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            firstName: undefined,
                        }),
                },
            },
            {
                accessorKey: 'setting_value',
                header: 'Value',
                muiEditTextFieldProps: {
                    required: true,
                    error: !!validationErrors?.firstName,
                    helperText: validationErrors?.firstName,
                    onFocus: () =>
                        setValidationErrors({
                            ...validationErrors,
                            firstName: undefined,
                        }),
                },
            },
            {
                accessorKey: 'created_at',
                header: 'Created At',
                enableEditing: false,
            },
            {
                accessorKey: 'updated_at',
                header: 'Updated At',
                enableEditing: false,
            }
        ], [validationErrors],
    );

    const handleFetch = async () => {
        setIsFetchingData(true);
        const response = await appSettingsService.getAppSettings();
        setData(response.data);
        setIsFetchingData(false);
    }

    const handleCreate = async (values) => {
        setIsCreatingData(true);
        await appSettingsService.createAppSetting({
            "setting_key": values.values.setting_key,
            "setting_value": values.values.setting_value
        });
        handleFetch();
        table.setCreatingRow(null);
        setIsCreatingData(false);
    };

    const handleUpdate = async (values) => {
        setIsUpdatingData(true);
        const response = await appSettingsService.updateAppSetting(values.values.id, {
            "setting_key": values.values.setting_key,
            "setting_value": values.values.setting_value
        });

        const index = data.indexOf(row => row.id == values.values.id);
        if (index !== -1) {
            arr[index] = response.data;
        }
        table.setEditingRow(null);
        setIsUpdatingData(false);
    };

    const handleDelete = async (id) => {
        setIsDeletingData(true);
        await appSettingsService.deleteAppSetting(id);
        setData(data.filter(row => row.id != id));
        setIsDeletingData(false);
    };

    const openDeleteConfirmModal = (row) => {
        if (window.confirm('Are you sure you want to delete this setting?')) {
            handleDelete(row.original.id);
        }
    };

    const table = useMaterialReactTable({
        columns,
        data: data,
        createDisplayMode: 'modal',
        editDisplayMode: 'modal',
        enableEditing: true,
        getRowId: (row) => row.id,
        muiToolbarAlertBannerProps: isFetchingData
            ? {
                color: 'error',
                children: 'Error loading data',
            }
            : undefined,
        muiTableContainerProps: {
            sx: {
                minHeight: '500px',
            },
        },
        onCreatingRowCancel: () => setValidationErrors({}),
        onCreatingRowSave: handleCreate,
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleUpdate,
        renderCreateRowDialogContent: ({ table, row, internalEditComponents }) => (
            <>
                <DialogTitle variant="h3">Create New Setting</DialogTitle>
                <DialogContent
                    sx={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}
                >
                    {internalEditComponents}
                </DialogContent>
                <DialogActions>
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </DialogActions>
            </>
        ),
        renderEditRowDialogContent: ({ table, row, internalEditComponents }) => (
            <>
                <DialogTitle variant="h3">Edit Setting</DialogTitle>
                <DialogContent
                    sx={{ display: 'flex', flexDirection: 'column', gap: '1.5rem' }}
                >
                    {internalEditComponents}
                </DialogContent>
                <DialogActions>
                    <MRT_EditActionButtons variant="text" table={table} row={row} />
                </DialogActions>
            </>
        ),
        renderRowActions: ({ row, table }) => (
            <Box sx={{ display: 'flex', gap: '1rem' }}>
                <Tooltip title="Edit">
                    <IconButton onClick={() => table.setEditingRow(row)}>
                        <EditIcon />
                    </IconButton>
                </Tooltip>
                <Tooltip title="Delete">
                    <IconButton color="error" onClick={() => openDeleteConfirmModal(row)}>
                        <DeleteIcon />
                    </IconButton>
                </Tooltip>
            </Box>
        ),
        renderTopToolbarCustomActions: ({ table }) => (
            <Button
                variant="contained"
                onClick={() => {
                    table.setCreatingRow(true);
                }}
            >
                Create New Setting
            </Button>
        ),
        state: {
            isLoading: isFetchingData,
            isSaving: isDeletingData || isCreatingData || isUpdatingData,
            showAlertBanner: isFetchError,
            showProgressBars: isFetchingData,
        }
    });

    return <MaterialReactTable table={table} />;
}