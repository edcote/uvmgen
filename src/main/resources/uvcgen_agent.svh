`ifndef UVCGEN_AGENT__SV
`define UVCGEN_AGENT__SV

class uvcgen_agent extends uvm_agent;
    typedef uvm_sequencer#(uvc_item) uvcgen_sequencer;

    `uvm_component_utils(uvcgen_agent)

    uvcgen_config                    m_config;
    uvcgen_sequencer                 m_sequencer;
    uvcgen_driver                    m_driver;
    uvcgen_monitor                   m_monitor;
    virtual uvcgen_if                vif;

    virtual function void build_phase(uvm_phase phase);
        super.build_phase(phase);

        if (!uvm_config_db#(virtual uvcgen_if)::get(this, "", vif))
            `uvm_fatal("NOVIF", {"virtual interface must be set for: ", get_full_name()});

        if (!uvm_config_db#(uvcgen_config)::get(this, "", m_config))
            `uvm_fatal("NOCFGOBJ", {"configuration object must be set for:", get_full_name()});

        if (get_is_active() != m_config.is_active)
            `uvm_fatal("ILLEGALCFG", {"configuration object parameter 'is_active' doesn't match config_db value for: ", get_full_name()});

        m_monitor = uvcgen_monitor::type_id::create("m_monitor", this);

        if (get_is_active() == UVM_ACTIVE) begin
            m_sequencer = uvcgen_sequencer::type_id::create("m_sequencer", this);
            m_driver = uvcgen_driver::type_id::create("m_driver", this);
        end
    endfunction : build_phase

    virtual function void connect_phase(uvm_phase phase);
        super.connect_phase(phase);

        if (is_active == UVM_ACTIVE)
            m_driver.seq_item_port.connect(m_sequencer.seq_item_export);
    endfunction : connect_phase

    function new (string name, uvm_component parent);
        super.new (name, parent);
    endfunction : new

endclass : uvcgen_agent

`endif //!UVCGEN_AGENT__SV