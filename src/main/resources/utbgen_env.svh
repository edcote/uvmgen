`ifndef UTBGEN_ENV__SV
`define UTBGEN_ENV__SV

class utbgen_env extends uvm_env;
    `uvm_component_utils(utbgen_env)

    utbgen_config            m_config;
    utbgen_virtual_sequencer m_sequencer;

    virtual function void build_phase(uvm_phase phase);
        super.build_phase(phase);

        if (!uvm_config_db#(utbgen_config)::get(this, "", m_config))
            `uvm_fatal("NOCFGOBJ", {"configuration object must be set for:", get_full_name()});

        if (m_config.is_active == UVM_ACTIVE) begin
            m_sequencer = utbgen_virtual_sequencer::type_id::create("m_sequencer", this);
        end
    endfunction : build_phase

    virtual function void connect_phase(uvm_phase phase);
        super.connect_phase(phase);
    endfunction : connect_phase

    function new (string name, uvm_component parent);
        super.new (name, parent);
    endfunction : new

endclass : utbgen_env

`endif //!UTBGEN_ENV__SV