`ifndef UVCGEN_DRIVER__SV
`define UVCGEN_DRIVER__SV

class uvcgen_driver extends uvm_driver#(uvcgen_item);
    typedef uvm_analysis_port#(uvcgen_item) analysis_port;

    `uvm_component_utils(uvcgen_driver)

    analysis_port                           ap;
    uvcgen_config                           m_config;

    virtual uvcgen_if                       vif;

    function void build_phase(uvm_phase phase);
        super.build_phase(phase);

        if (!uvm_config_db#(virtual uvcgen_if)::get(this, "", vif))
            `uvm_fatal("NOVIF", {"virtual interface must be set for: ", get_full_name()});

        if (!uvm_config_db#(uvcgen_config)::get(this, "", m_config))
            `uvm_fatal("NOCFGOBJ", {"configuration object must be set for:", get_full_name()});
    endfunction : build_phase

    task run_phase(uvm_phase phase);
        forever begin
            uvcgen_item item;
            @(vif.cb);
            seq_item_port.get_next_item(item);
            // FIXME: `uvm_info(get_name(), {\"received:, \", item.convert2string()}, UVM_MEDIUM}
            drive_item(item);
            ap.write(item.copy());
            seq_item_port.done();
        end
    endtask

    virtual task drive_item(uvcgen_item item);
    endtask

    function new (string name, uvm_component parent);
        super.new (name, parent);
    endfunction : new

endclass : uvcgen_driver

`endif //!UVCGEN_DRIVER__SV