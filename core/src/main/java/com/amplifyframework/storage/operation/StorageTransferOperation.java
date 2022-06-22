/*
 * Copyright 2022 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amplifyframework.storage.operation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.async.Resumable;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.storage.StorageException;
import com.amplifyframework.storage.result.StorageTransferProgress;
import com.amplifyframework.storage.result.StorageTransferResult;

import java.util.UUID;

/**
 * Base operation type for any transfer behavior on the Storage category.
 *
 * @param <R> type of the request object
 * @param <T> type of success transfer result
 */
public abstract class StorageTransferOperation<R, T extends StorageTransferResult>
        extends AmplifyOperation<R> implements Resumable, Cancelable {

    /**
     * Consumer that notifies transfer progress.
     */
    @NonNull
    protected Consumer<StorageTransferProgress> onProgress;

    /**
     * Consumer that notifies transfer success.
     */
    @NonNull
    protected Consumer<T> onSuccess;

    /**
     * Consumer that notifies transfer error.
     */
    @NonNull
    protected Consumer<StorageException> onError;

    /**
     * Unique identifier for the transfer.
     */
    @NonNull
    private final UUID transferId;

    /**
     * Constructs a new AmplifyOperation.
     * @param amplifyOperationRequest The request object of the operation
     * @param transferId Unique identifier for tracking in local device queue
     * @param onProgress Notified upon advancements in upload progress
     * @param onSuccess Will be notified when results of upload are available
     * @param onError Notified when upload fails with an error
     */
    public StorageTransferOperation(
            @Nullable R amplifyOperationRequest,
            @NonNull UUID transferId,
            @NonNull Consumer<StorageTransferProgress> onProgress,
            @NonNull Consumer<T> onSuccess,
            @NonNull Consumer<StorageException> onError
    ) {
        super(CategoryType.STORAGE, amplifyOperationRequest);
        this.transferId = transferId;
        this.onProgress = onProgress;
        this.onSuccess = onSuccess;
        this.onError = onError;
    }

    /**
     * Gets a unique identifier of a transfer operation held in the device queue.
     * Holding on to this id allows you to later query for the queued operation.
     *
     * @return device queue transfer id
     */
    @NonNull
    public UUID getTransferId() {
        return transferId;
    }

    /**
     * Provide a Consumer to receive transfer progress updates.
     *
     * @param onProgress Consumer which provides incremental progress updates
     */
    public void setOnProgress(@NonNull Consumer<StorageTransferProgress> onProgress) {
        this.onProgress = onProgress;
    }

    /**
     * Provide a Consumer to receive successful transfer result.
     *
     * @param onSuccess Consumer which provides a successful transfer result
     */
    public void setOnSuccess(@NonNull Consumer<T> onSuccess) {
        this.onSuccess = onSuccess;
    }

    /**
     * Provide a Consumer to receive transfer errors.
     *
     * @param onError Consumer which provides transfer errors
     */
    public void setOnError(@NonNull Consumer<StorageException> onError) {
        this.onError = onError;
    }
}
