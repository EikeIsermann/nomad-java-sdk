package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.RaftConfiguration;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * API for operating a Nomad cluster,
 * exposing the <a href="https://www.nomadproject.io/api/operator.html">operator</a> functionality of the
 * <a href="https://www.nomadproject.io/docs/http/index.html">Nomad HTTP API</a>.
 */
public class OperatorApi extends ApiBase {

    OperatorApi(NomadApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Gets the cluster's Raft configuration.
     *
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-raft-configuration">{@code GET /v1/operator/raft/configuration}</a>
     */
    public NomadResponse<RaftConfiguration> raftGetConfiguration() throws IOException, NomadException {
        return raftGetConfiguration(null);
    }

    /**
     * Gets the cluster's Raft configuration.
     *
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#read-raft-configuration">{@code GET /v1/operator/raft/configuration}</a>
     */
    public NomadResponse<RaftConfiguration> raftGetConfiguration(
            @Nullable QueryOptions<RaftConfiguration> options
    ) throws IOException, NomadException {
        return executeServerQuery(
                "/v1/operator/raft/configuration",
                options,
                NomadJson.parserFor(RaftConfiguration.class));
    }

    /**
     * Removes a raft peer from the cluster.
     *
     * @param address ip:port address of the peer to remove
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#remove-raft-peer">{@code DELETE /v1/operator/raft/peer}</a>
     */
    public NomadResponse<Void> raftRemovePeerByAddress(String address)
            throws IOException, NomadException {

        return raftRemovePeerByAddress(address, null);
    }

    /**
     * Removes a raft peer from the cluster.
     *
     * @param address ip:port address of the peer to remove
     * @param options options controlling how the request is performed
     * @throws IOException    if there is an HTTP or lower-level problem
     * @throws NomadException if the response signals an error or cannot be deserialized
     * @see <a href="https://www.nomadproject.io/api/operator.html#remove-raft-peer">{@code DELETE /v1/operator/raft/peer}</a>
     */
    public NomadResponse<Void> raftRemovePeerByAddress(String address, @Nullable WriteOptions options)
            throws IOException, NomadException {

        return executeServerAction(
                delete(
                        uri("/v1/operator/raft/peer").addParameter("address", address),
                        options
                ),
                null
        );
    }
}
