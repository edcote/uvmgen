#!/bin/sh
exec scala "$0" "$@"
!#

// Reference:
// https://www.doulos.com/knowhow/sysverilog/uvm/easier_uvm_guidelines/detail/#link0

implicit val name: String = "tilelink"

def interface(implicit name: String) = {
  val guard = name.toUpperCase+"_IF__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |interface ${name}_if (input clock);
   |endinterface : ${name}_if
   |
   |`endif // $guard
  """.stripMargin.trim
}


def packge(implicit name: String) = {
  val guard = name.toUpperCase+"_PKG__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |package ${name}_pkg;
   |  `include "uvm_macros.svh"
   |  import uvm_pkg::*;
   |
   |  timeunit 1ns;
   |  timeprecision 1ps;
   |
   |  `include "${name}_item.sv"
   |  `include "${name}_config.sv"
   |  `include "${name}_driver.sv"
   |  `include "${name}_monitor.sv"
   |  `include "${name}_agent.sv"
   |  `include "${name}_agent.sv"
   |  `include "${name}_coverage.sv"
   |  `include "${name}_seq_lib.sv"
   |
   |endpackage : ${name}_pkg
   |
   |//`endif //$guard
   """.stripMargin.trim
}

def item(implicit name: String) = {
  val guard = name.toUpperCase+"_ITEM__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |class ${name}_item extends uvm_sequence_item;
   |  `uvm_object_utils(${name}_item)
   |  `uvm_declare_p_sequencer(${name}_sequencer)
   |
   |    function new (string name="${name}_item");
   |      super.new(name);
   |    endfunction : new
   |
   |endclass : ${name}_item
   |
   |`endif // $guard
  """.stripMargin.trim
}

def config(implicit name: String) = {
  val guard = name.toUpperCase+"_CONFIG__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |class ${name}_config extends uvm_object;
   |  `uvm_object_utils(${name}_config)
   |
   |    function new (string name="${name}_item");
   |      super.new(name);
   |    endfunction : new
   |
   |endclass : ${name}_config
   |
   |`endif // $guard
  """.stripMargin.trim
}

def driver(implicit name: String) = {
  val guard = name.toUpperCase+"_DRIVER__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |class ${name}_driver extends uvm_driver #(${name}_item);
   |  `uvm_component_utils(${name}_driver)
   |
   |  function new(string name, uvm_component parent);
   |    super.new(name, parent);
   |  endfunction : new
   |
   |endclass : ${name}_driver
   |
   |`endif // $guard
  """.stripMargin.trim
}

def monitor(implicit name: String) = {
  val guard = name.toUpperCase+"_MONITOR__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |class ${name}_monitor extends uvm_monitor #(${name}_item);
   |  `uvm_component_utils(${name}_monitor)
   |
   |  function new(string name, uvm_component parent);
   |    super.new(name, parent);
   |  endfunction : new
   |
   |endclass : ${name}_monitor
   |
   |`endif // $guard
  """.stripMargin.trim
}

def agent(implicit name: String) = {
  val guard = name.toUpperCase+"_AGENT__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |class ${name}_agent extends uvm_agent;
   |  `uvm_component_utils(${name}_agent)
   |
   |  ${name}_config m_config;
   |  ${name}_driver m_driver;
   |  ${name}_monitor m_monitor;
   |  ${name}_coverage m_coverage;
   |
   |  function new(string name, uvm_component parent);
   |    super.new(name, parent);
   |  endfunction : new
   |
   |  function void build_phase(uvm_phase phase);
   |    super.build_phase(phase);
   |
   |    if (!uvm_config_db #(${name}_config)::get(this, "", "config", m_config))
   |      `uvm_error(get_type_name(), "Agent config object is missing from config_db")
   |  endfunction : build_phase
   |
   |  function void connect_phase(uvm_phase phase);
   |    if (get_is_active() == UVM_ACTIVE)
   |      m_driver.seq_item_port.connect(m_sequencer.seq_item_export);
   |  endfunction : connect_phase
   |
   |endclass : ${name}_agent
   |
   |`endif // $guard
  """.stripMargin.trim
}

def coverage(implicit name: String) = {
  val guard = name.toUpperCase+"_COVERAGE__SV"
  s"""
   |`ifndef $guard
   |`define $guard
   |
   |class ${name}_coverage extends uvm_subscriber #(${name}_item);
   |  `uvm_component_utils(${name}_coverage)
   |
   |  function new(string name, uvm_component parent);
   |    super.new(name, parent);
   |  endfunction : new
   |
   |endclass : ${name}_coverage
   |
   |`endif // $guard
  """.stripMargin.trim
}

Seq(interface, packge, item, config, driver, monitor, agent, coverage).foreach { f => print(f+"\n\n")}
