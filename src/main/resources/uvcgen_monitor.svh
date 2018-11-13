`ifndef UVCGEN_MONITOR__SV
`define UVCGEN_MONITOR__SV

class uvcgen_monitor extends uvm_monitor;
    typedef uvm_analysis_port#(uvcgen_item) analysis_port;

    `uvm_component_utils(uvcgen_monitor)

    analysis_port                           ap;
    uvcgen_config                           m_config;

    virtual uvcgen_if                       vif;

    virtual function void build_phase(uvm_phase phase);
        super.build_phase(phase);
        if (!uvm_config_db#(virtual uvcgen_if)::get(this, "", vif))
            `uvm_fatal("NOVIF", {"virtual interface must be set for: ", get_full_name()});

        if (!uvm_config_db#(uvcgen_config)::get(this, "", m_config))
            `uvm_fatal("NOCFGOBJ", {"configuration object must be set for:", get_full_name()});
    endfunction : build_phase

    virtual function void connect_phase(uvm_phase phase);
        super.connect_phase(phase);
    endfunction : connect_phase

    task run_phase(uvm_phase phase);
        forever begin
            uvcgen_item item = new;
            collect_transaction(item);
            `uvm_info(get_name(), {"received: ", item.convert2string()}, UVM_MEDIUM})
            ap.write(item.copy());
        end
    endtask

    virtual task collect_transaction(uvcgen_item item);
        @(vif.cb);
    endtask

    function new (string name, uvm_component parent);
        super.new (name, parent);
    endfunction : new

endclass : uvcgen_monitor

`endif //!UVCGEN_MONITOR__SV