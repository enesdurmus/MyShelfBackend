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
import userService from 'services/userService';

export default function UserManagement() {
    const [validationErrors, setValidationErrors] = useState({});
    const [data, setData] = useState([]);
    const [totalElements, setTotalElements] = useState();
    const [isFetchingData, setIsFetchingData] = useState(false);
    const [isCreatingData, setIsCreatingData] = useState(false);
    const [isUpdatingData, setIsUpdatingData] = useState(false);
    const [isDeletingData, setIsDeletingData] = useState(false);
    const [isFetchError, setIsFetchError] = useState(false);

    const [pagination, setPagination] = useState({
        pageIndex: 0,
        pageSize: 5,
    });

    useEffect(() => {
        handleFetch();
    }, []);

    useEffect(() => {
        handleFetch();
    }, [pagination.pageIndex, pagination.pageSize]);


    const columns = useMemo(
        () => [
            {
                accessorKey: 'email',
                header: 'Email',
                enableEditing: false,
                size: 80,
            },
            {
                accessorKey: 'display_name',
                header: 'Display Name',
                enableEditing: false,
            },
        ], [validationErrors],
    );

    const handleFetch = async () => {
        setIsFetchingData(true);
        const response = await userService.getUsersByPagination(pagination.pageIndex, pagination.pageSize);
        setTotalElements(response.data.total_elements);
        setData(response.data.users);
        setIsFetchingData(false);
    }

    const handleUpdate = async (values) => {
    };

    const handleDelete = async (id) => {
    };

    const openDeleteConfirmModal = (row) => {
        if (window.confirm('Are you sure you want to delete this User?')) {
            handleDelete(row.original.id);
        }
    };

    const table = useMaterialReactTable({
        columns,
        data: data,
        createDisplayMode: 'modal',
        editDisplayMode: 'modal',
        enableEditing: true,
        onPaginationChange: setPagination,
        manualPagination: true,
        rowCount: totalElements,
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
        onEditingRowCancel: () => setValidationErrors({}),
        onEditingRowSave: handleUpdate,
        renderEditRowDialogContent: ({ table, row, internalEditComponents }) => (
            <>
                <DialogTitle variant="h3">Edit User</DialogTitle>
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
        state: {
            pagination,
            isLoading: isFetchingData,
            isSaving: isDeletingData || isCreatingData || isUpdatingData,
            showAlertBanner: isFetchError,
            showProgressBars: isFetchingData,
        }
    });

    return <MaterialReactTable table={table} />;
}
