export const getPeers = () => {
    return [
        {
            label: "liberator1",
            incomingPeers: [
                {
                    instanceName: "3f9d763d-0b5d-4e01-9528-4ad7c5388fa6",
                    label: "transformer1",
                    providedServices: ["transformer-demosrc-service"]
                },
                {
                    instanceName: "3f9d763d-0b5d-4e01-9528-4ad7c5388fa6",
                    label: "transformer2",
                    providedServices: ["transformer-demosrc-service"]
                }
            ],
            outgoingPeers: []
        },
        {
            label: "liberator2",
            incomingPeers: [
                {
                    instanceName: "3f9d763d-0b5d-4e01-9528-4ad7c5388fa6",
                    label: "transformer2",
                    providedServices: ["transformer-demosrc-service"]
                }
            ],
            outgoingPeers: []
        },
        {
            label: "liberator3",
            incomingPeers: [
                {
                    instanceName: "3f9d763d-0b5d-4e01-9528-4ad7c5388fa6",
                    label: "transformer4",
                    providedServices: ["transformer-demosrc-service"]
                }
            ],
            outgoingPeers: []
        },
        {
            label: "liberator4",
            incomingPeers: [
                {
                    instanceName: "3f9d763d-0b5d-4e01-9528-4ad7c5388fa6",
                    label: "transformer5",
                    providedServices: ["transformer-demosrc-service"]
                }
            ],
            outgoingPeers: []
        },
        {
            label: "transformer1",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator1",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 59,
                    latency: 28,
                    avgLatency: 20
                }
            ]
        },
        {
            label: "transformer2",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator1",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 9,
                    latency: 33,
                    avgLatency: 76
                },
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator2",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 2,
                    latency: 67,
                    avgLatency: 65
                }
            ]
        },
        {
            label: "transformer3",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator2",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 4,
                    latency: 34,
                    avgLatency: 23
                }
            ]
        },
        {
            label: "transformer4",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator3",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 59,
                    latency: 28,
                    avgLatency: 20
                },
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator2",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 59,
                    latency: 28,
                    avgLatency: 20
                }
            ]
        },
        {
            label: "transformer5",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator4",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 59,
                    latency: 28,
                    avgLatency: 20
                }
            ]
        },
        {
            label: "PricingAdapter1",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "transformer1",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 5,
                    latency: 38,
                    avgLatency: 24
                }
            ]
        },
        {
            label: "PricingAdapter2",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "transformer2",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 69,
                    latency: 5,
                    avgLatency: 11
                }
            ]
        },
        {
            label: "PricingAdapter3",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator1",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 2,
                    latency: 75,
                    avgLatency: 65
                }
            ]
        },
        {
            label: "PricingAdapter4",
            isAdapter: true,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "liberator2",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 54,
                    latency: 123,
                    avgLatency: 87
                }
            ]
        },
        {
            label: "PricingAdapter5",
            isAdapter: true,
            isWorking: 1,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "transformer3",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 48,
                    latency: 23,
                    avgLatency: 11
                }
            ]
        },
        {
            label: "PricingAdapter6",
            isAdapter: true,
            isWorking: 2,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "transformer4",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 48,
                    latency: 23,
                    avgLatency: 11
                }
            ]
        },
        {
            label: "PricingAdapter7",
            isAdapter: true,
            isWorking: 1,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "transformer5",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 48,
                    latency: 23,
                    avgLatency: 11
                }
            ]
        },
        {
            label: "PricingAdapter8",
            isAdapter: true,
            isWorking: 2,
            incomingPeers: [],
            outgoingPeers: [
                {
                    instanceName: "611461ef-302b-4477-b36d-6dcc798338b5",
                    address: "ubuntu",
                    port: 15001,
                    label: "transformer2",
                    requestedServices: [
                        "transformer-trep-service",
                        "transformer-demosrc-service"
                    ],
                    updates: 48,
                    latency: 23,
                    avgLatency: 11
                }
            ]
        }
    ];
};
